package com.motorshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorshowcase.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
