package com.davez.citylocator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="state")
public class State {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="state")
	private String state;
	
	@Column(name="abbrev")
	private String abbrev;
	
	@Column(name="neighbors")
	private String neighbors;
	
	//needed for Hibernate
	public State() {
		
	}

	public State(String state, String abbrev, String neighbors) {
		super();
		this.state = state;
		this.abbrev = abbrev;
		this.neighbors = neighbors;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public String getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(String neighbors) {
		this.neighbors = neighbors;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", state=" + state + ", abbrev=" + abbrev + ", neighbors=" + neighbors + "]";
	}
	
	
}
