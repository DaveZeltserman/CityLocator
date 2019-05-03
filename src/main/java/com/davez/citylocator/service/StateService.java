package com.davez.citylocator.service;

import java.util.List;

import com.davez.citylocator.entity.State;

public interface StateService {
	List<State> findAll();
	String getStateName(String abbrev);
	String getStateAbbrev(String stateName);
	String getNeighbors(String abbrev);
}
