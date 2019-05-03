package com.davez.citylocator.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davez.citylocator.DistanceCalculator;
import com.davez.citylocator.OpenStreetMapUtils;
import com.davez.citylocator.dao.CityRepository;
import com.davez.citylocator.entity.City;
import com.davez.citylocator.entity.LargeCity;
import com.davez.citylocator.entity.SearchApiRequest;
import com.davez.citylocator.entity.SearchApiResults;

@Service
public class CityServiceImpl implements CityService {

	private CityRepository repo;
	private OpenStreetMapUtils mapUtils;
	private DistanceCalculator distCalc;
		
	@Autowired
	public CityServiceImpl(CityRepository repo) {
		this.repo = repo;
		this.mapUtils = OpenStreetMapUtils.getInstance();
		this.distCalc = DistanceCalculator.getInstance();
	}
	
	@PostConstruct
	public void fixCityTable() {
		// find and complete all city objects in DB that are not active
		
		Map<String, String> coords;
		
		List<City> cities = repo.findByActiveFalse();
		for (City city : cities) {
			System.out.println("Getting GPS coordinates for " + city.getCity() + " " + city.getState());
			coords = OpenStreetMapUtils.getInstance().getCoordinates(city.getCity() +", " + city.getState()+ " US");
			if (coords == null) {
				System.out.println("Problem locating "+ city.getCity() + " " + city.getState());
				continue;
			}
			
			if (!coords.containsKey("lat")) {
				System.out.println("Unable to get coordinates for "+ city.getCity() + " " + city.getState());
				continue;
			}
			city.setLat(Double.parseDouble(coords.get("lat")));
			city.setLon(Double.parseDouble(coords.get("lon")));
			city.setActive(true);
			
			System.out.println(city.toString());
			repo.save(city);
		}
	}
	
	
	@Override
	public Map<String, String> getCityCoordinates(String cityName, String stateName) {
		Map<String, String> coords;
		coords = OpenStreetMapUtils.getInstance().getCoordinates(cityName +", " + stateName + " US");
		return coords;
	}

	@Override
	public List<LargeCity> getLargeCities(String stateName, double lat1, double lon1, int maxDistance) {
		
		List<LargeCity> largecities = new ArrayList<>();
				
		// get list of all large cities from the same state
		
		List<City> cities = repo.findByState(stateName);
		
		// for each large city in state, calculate distance to this city/town
		
		LargeCity closestCity = new LargeCity();
		boolean closestCityFlag = false;
		
		for (City city : cities) {
			
			// if GPS cooridinates not yet discovered, skip
			if (!city.isActive()) {
				continue;
			}
		
			// calculate distance to target city/town
			
			double d = distCalc.distance(lat1, lon1, city.getLat(), city.getLon(), "M");
			
			// special case: keep track of the closest large city found
			if (!closestCityFlag) {
				closestCity.setCity(city.getCity());
				closestCity.setState(city.getState());
				closestCity.setDistance((int) d);
				closestCityFlag = true;
			} else {
				if ((int) d < closestCity.getDistance()) {
					closestCity.setCity(city.getCity());
					closestCity.setState(city.getState());
					closestCity.setDistance((int) d);
				}
			}
			
			if (d <= maxDistance) {
				LargeCity lc = new LargeCity(city.getCity(), city.getState(), (int) d);
				largecities.add(lc);
			}
		}

		// special case: if no large cities found within maxDistance, add closest city found
		if (largecities.isEmpty()) {
			largecities.add(closestCity);
		}
		
		return largecities;
	}
	
	@Override
	public List<City> findByActiveFalse() {
		
		return repo.findByActiveFalse();
	}

	@Override
	public List<City> findByState(String stateName) {
		
		return repo.findByState(stateName);
	}

	@Override
	public City findByCityAndState(String cityName, String stateName) {
		
		return repo.findByCityAndState(cityName, stateName);
	}

	@Override
	public void save(City city) {
		
		repo.save(city);

	}

	@Override
	public SearchApiResults search(SearchApiRequest request, StateService stateService) {
		SearchApiResults result = new SearchApiResults();
		Map<String, String> coords = this.getCityCoordinates(request.getCity(), request.getState());
		
		if(!coords.containsKey("lon")) {
			result.setMessage("Unable to find " + request.getCity() + " " + request.getState() + ". Please check spelling.");
			return result;
		}
		
		// add logic to make sure state returned is the same one entered!
		// if not, generate error message
		
		String abbrev;
		if (request.getState().length() == 2) {
			abbrev = request.getState().toUpperCase();
		} else {
			abbrev = stateService.getStateAbbrev(request.getState());
		}
		
		String stateFound = coords.get("state").toUpperCase().trim();
		
		if (stateFound.length() != 2) {
			stateFound = stateService.getStateAbbrev(stateFound);
		}
		
		if (stateFound.equals("unknown")) {
			result.setMessage("Unable to find " + request.getCity() + " " + request.getState() + ". Please check spelling.");
			return result;
		}
		
		// at this point, stateFound is the abbrev for the state returned by getCityCoordinates
		
		if (!abbrev.equals(stateFound)) {
			result.setMessage("Did you mean " + request.getCity() + " " + stateFound + " instead of " + request.getCity() + " " + request.getState() + "? Please check spelling.");
			return result;			
		}
		
		List<LargeCity> temp;
		
		LargeCity closestCity = null;
		int maxDistance = request.getMaxDistance();
		
		String states = abbrev + " " + stateService.getNeighbors(abbrev);
		String[] arr = states.split(" ");
		for (int i = 0; i < arr.length; i++) {
			System.out.println("Checking large cities in state: " + arr[i]);
			temp = this.getLargeCities(arr[i], 
					Double.parseDouble(coords.get("lat")), 
					Double.parseDouble(coords.get("lon")), 
					maxDistance);
			
			// check for special case where getLargeCities unable to find any large cities within maxDistance
			// and instead returns closest city found
			
			if (temp.size() == 1) {
				if (temp.get(0).getDistance() > maxDistance) {
					if (closestCity == null) {
						closestCity = temp.get(0);
					} else {
						// check whether this closest city is closer that previous one from a possibly bordering state
						
						if(temp.get(0).getDistance() < closestCity.getDistance()) {
							closestCity = temp.get(0);
						}
					}
					
					// make sure large city isn't added to collection
					
					temp.clear();
				}
			}
			
			if (!temp.isEmpty()) {
				result.getLargeCities().addAll(temp);
			}
						
		}

		if (!result.getLargeCities().isEmpty()) {
			Collections.sort(result.getLargeCities());
		} else {
			if (closestCity != null) {
				result.setMessage("No large city found within " + maxDistance + " miles. The closest large city found is " + 
						closestCity.getCity() + " " + closestCity.getState() + ", which is " + closestCity.getDistance() +
						" miles away");
			}
		}
		return result;
	}

}
