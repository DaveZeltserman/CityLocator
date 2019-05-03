package com.davez.citylocator.service;

import java.util.List;
import java.util.Map;

import com.davez.citylocator.entity.City;
import com.davez.citylocator.entity.LargeCity;
import com.davez.citylocator.entity.SearchApiRequest;
import com.davez.citylocator.entity.SearchApiResults;

public interface CityService {
	public List<City> findByActiveFalse();
	public List<City> findByState(String stateName);
	public City findByCityAndState(String cityName, String stateName);
	public List<LargeCity> getLargeCities(String stateName, double lat, double lon, int maxDistance);
	public Map<String, String> getCityCoordinates(String cityName, String stateName);
	public SearchApiResults search(SearchApiRequest request, StateService stateService);
	public void save(City city);
}
