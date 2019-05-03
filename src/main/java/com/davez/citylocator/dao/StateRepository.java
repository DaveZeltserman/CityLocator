package com.davez.citylocator.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davez.citylocator.entity.State;

public interface StateRepository extends JpaRepository<State, Integer> {

}
