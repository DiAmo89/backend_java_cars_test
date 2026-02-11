package app.service;

import app.domain.Car;
import app.repository.CarRepository;
import app.repository.CarRepositoryHibernate;
import app.repository.CarRepositoryJdbc;
import app.repository.CarRepositoryMap;

import java.util.List;

public class CarServiceImpl implements CarService {
    private final CarRepository repository = new CarRepositoryHibernate();

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = repository.findAll();
        return cars;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Car getById(Long numericId) {
        return null;
    }

}
