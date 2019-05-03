package com.davez.citylocator.entity;

public class LargeCity implements Comparable<Object> {
	private String city;
	private String state;
	private String maxDistance;
	private String message;
	private int distance;
	
	public LargeCity() {
		
	}
	
	public LargeCity(String city, String state, int distance) {
		this.city = city;
		this.state = state;
		this.message="";
		this.distance = distance;
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

	public String getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(String maxDistance) {
		this.maxDistance = maxDistance;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "LargeCity [city=" + city + ", state=" + state + ", distance=" + distance + "]";
	}

	@Override
	public int compareTo(Object arg) {
		LargeCity lc = (LargeCity) arg;
		return this.distance - lc.distance;
	}
	
	
}
