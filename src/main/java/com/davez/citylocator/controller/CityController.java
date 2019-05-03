package com.davez.citylocator.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.ReportAsSingleViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davez.citylocator.entity.LargeCity;
import com.davez.citylocator.entity.SearchApiRequest;
import com.davez.citylocator.entity.SearchApiResults;
import com.davez.citylocator.service.CityService;
import com.davez.citylocator.service.StateService;

@Controller
@RequestMapping("/searchmap")
public class CityController {

	@Autowired
	private StateService stateService;
	
	private CityService cityService;
	
	@Autowired
	public CityController(CityService cs) {
		this.cityService = cs;
	}
	
	@GetMapping("/main")
	public String mainForm(Model theModel) {
		
		List<LargeCity> largeCities = new ArrayList<>();
		theModel.addAttribute("largeCities", largeCities);
		LargeCity city = new LargeCity();
		theModel.addAttribute("city", city);
		
		return "searchmap/main";
	}
	
	@PostMapping("/search")
	public String search(Model theModel, @ModelAttribute("city") LargeCity theCity) {

		SearchApiRequest request = new SearchApiRequest();
		request.setCity(theCity.getCity());
		request.setState(theCity.getState());
		request.setMaxDistance(Integer.parseInt(theCity.getMaxDistance()));
	
		SearchApiResults result = cityService.search(request, stateService);
		theCity.setMessage(result.getMessage());
		theModel.addAttribute("city", theCity);
		theModel.addAttribute("largeCities", result.getLargeCities());
		return "searchmap/main";

	}
}
