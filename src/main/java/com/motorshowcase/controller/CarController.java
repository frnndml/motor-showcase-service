package com.motorshowcase.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.motorshowcase.model.Car;
import com.motorshowcase.service.CarService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("cars")
public class CarController {

	private CarService service;
	
	@GetMapping
	public ResponseEntity<List<Car>> findAll() {
		List<Car> cars = service.list();
		if(cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ok(cars);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Car car = service.fetch(id);
		if(car == null) {
			String message = String.format("Car with id %s not found.", id);
			return new ResponseEntity<String>(message, NOT_FOUND);
		}
		return ok(car);
	}
	
	@PostMapping
	public ResponseEntity<Car> save(@RequestBody Car car, UriComponentsBuilder ucBuilder) {
		Car saved = service.save(car);
		return ResponseEntity.created(ucBuilder.path("/cars/{id}").buildAndExpand(saved.getId()).toUri()).build();
	}
	
	@PutMapping
	public ResponseEntity<Car> update(@RequestBody Car car) {
		Car updated = service.update(car);
		return ok(updated);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Car car = service.fetch(id);
		if(car == null) {
			String message = String.format("Unable to delete. Car with id %s not found.", id);
			return new ResponseEntity<String>(message, NOT_FOUND);
		}		
		service.delete(car);
		return ResponseEntity.noContent().build();
	}
}
