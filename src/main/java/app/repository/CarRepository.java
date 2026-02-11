package app.repository;

import app.domain.Car;

import java.util.List;

public interface CarRepository {
    Car save(Car car);

    List<Car> findAll();

    Car findById(Long id);

    void deleteById(Long id);

}
