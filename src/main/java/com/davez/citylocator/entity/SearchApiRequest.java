package com.davez.citylocator.entity;

public class SearchApiRequest {

	public String city;
	public String state;
	public int maxDistance;
	
	public SearchApiRequest() {
		
	}

	public SearchApiRequest(String city, String state, int maxDistance) {
		super();
		this.city = city;
		this.state = state;
		this.maxDistance = maxDistance;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	
}
