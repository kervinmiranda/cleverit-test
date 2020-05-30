package com.cleverit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleverit.entity.Car;

public interface IAuto extends JpaRepository<Car, Integer>{

}
