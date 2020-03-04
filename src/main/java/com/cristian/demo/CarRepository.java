package com.cristian.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CarRepository extends CrudRepository<Car, Long> {

    ArrayList<Car> findByManufacturer(String search);
}
