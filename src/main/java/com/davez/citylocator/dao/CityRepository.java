package com.davez.citylocator.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davez.citylocator.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	public List<City> findByActiveFalse();
	
	public List<City> findByState(String stateName);
	
	public City findByCityAndState(String cityName, String stateName); 
}
