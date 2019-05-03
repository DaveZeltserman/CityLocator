package com.davez.citylocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="lon")
	private double lon;
	
	@Column(name="lat")
	private double lat;
	
	@Column(name="active")
	private boolean active;

	// hibernate requires no arg constructor
	public City() {
		
	}
	
	public City(int id, String city, String state, double lon, double lat, boolean active) {
		super();
		this.id = id;
		this.city = city;
		this.state = state;
		this.lon = lon;
		this.lat = lat;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", city=" + city + ", state=" + state + ", lon=" + lon + ", lat=" + lat + ", active="
				+ active + "]";
	}
	
	
	
}
