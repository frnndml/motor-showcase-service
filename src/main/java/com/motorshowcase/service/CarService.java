package com.motorshowcase.service;

import java.util.List;

import com.motorshowcase.model.Car;

public interface CarService {

	Car fetch(Long id);

	List<Car> list();

	Car save(Car car);

	Car update(Car car);

	void delete(Car car);

}
