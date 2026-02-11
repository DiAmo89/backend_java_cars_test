package app.service;

import app.domain.Car;

import java.util.List;

public interface CarService {
    Car save(Car car);

    List<Car> getAll();

    void deleteById(Long id);

    Car getById(Long numericId);
}
