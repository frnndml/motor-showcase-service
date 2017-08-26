package com.motorshowcase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorshowcase.model.Car;
import com.motorshowcase.repository.CarRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

	private CarRepository repository;

	@Override
	public Car fetch(Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public List<Car> list() {
		return repository.findAll();
	}
	
	@Override
	public Car save(Car car) {
		return repository.save(car);
	}
	
	@Override
	public Car update(Car car) {
		return repository.save(car);
	}
	
	@Override
	public void delete(Car car) {
		repository.delete(car);
	}
}
