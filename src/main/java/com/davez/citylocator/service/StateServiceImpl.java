package com.davez.citylocator.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davez.citylocator.dao.StateRepository;
import com.davez.citylocator.entity.State;

@Service
public class StateServiceImpl implements StateService {

	private StateRepository repo;
	private Map<String, State> stateMapFindByAbbrev;
	private Map<String, State> stateMapFindByName;
	
	@Autowired
	public StateServiceImpl(StateRepository repo) {
		this.repo = repo;
		this.stateMapFindByAbbrev = new HashMap<>();
		this.stateMapFindByName = new HashMap<>();
	}
	
	@PostConstruct
	public void buildStateMaps() {
		List<State> states = findAll();
		for (State state: states) {
			stateMapFindByAbbrev.put(state.getAbbrev(), state);
			stateMapFindByName.put(state.getState().toUpperCase(), state);
		}
		
	}
	
	@Override
	public List<State> findAll() {
		
		return repo.findAll();
	}

	@Override
	public String getStateName(String abbrev) {
		
		State state = this.stateMapFindByAbbrev.get(abbrev);
		if (state == null) return "unknown";
		
		return state.getState();
	}

	@Override
	public String getStateAbbrev(String stateName) {
		
		State state = this.stateMapFindByName.get(stateName.toUpperCase());
		if (state == null) return "unknown";
		
		return state.getAbbrev();
	}

	@Override
	public String getNeighbors(String abbrev) {
		
		State state = this.stateMapFindByAbbrev.get(abbrev);
		if (state == null) return "unknown";
		
		return state.getNeighbors();
	}

}
