package com.davez.citylocator.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davez.citylocator.entity.LargeCity;
import com.davez.citylocator.entity.SearchApiRequest;
import com.davez.citylocator.entity.SearchApiResults;
import com.davez.citylocator.service.CityService;
import com.davez.citylocator.service.StateService;

@RestController
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	private StateService stateService;
	
	private CityService cityService;
	
	@Autowired
	public RestApiController(CityService cs) {
		this.cityService = cs;
	}
	
	@GetMapping("/search")
	public SearchApiResults search(@RequestBody SearchApiRequest request) {
		
		return cityService.search(request, stateService);
		
	}
}
