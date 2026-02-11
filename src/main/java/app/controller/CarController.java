package app.controller;

import app.domain.Car;
import app.service.CarService;
import app.service.CarServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class CarController {
    private final CarService service = new CarServiceImpl();

    public Car save(String brand, int year, BigDecimal price) {
        Car car = new Car(brand, year, price);
        return service.save(car);
    }

    public List<Car> getAll() {
        return service.getAll();
    }

    public void delete(Long id) {
        service.deleteById(id);
    }

    public Car findById(Long id) {
        return service.getById(id);
    }
}
