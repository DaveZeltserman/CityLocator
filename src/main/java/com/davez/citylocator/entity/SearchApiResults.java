package com.davez.citylocator.entity;

import java.util.ArrayList;
import java.util.List;

public class SearchApiResults {
	List<LargeCity> largeCities;
	String message;
	
	public SearchApiResults() {
		largeCities = new ArrayList<>();
		message = null;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<LargeCity> getLargeCities() {
		return largeCities;
	}
	
	
}
