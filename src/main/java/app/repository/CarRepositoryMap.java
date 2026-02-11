package app.repository;

import app.domain.Car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRepositoryMap implements CarRepository {
    private final Map<Long, Car> database = new HashMap<>();
    private long maxId;

    public CarRepositoryMap() {
        save(new Car("Volkswagen", 2010, new BigDecimal(10000)));
        save(new Car("Mazda", 2015, new BigDecimal(20000)));
        save(new Car("Honda", 2020, new BigDecimal(30000)));
    }

    @Override
    public Car save(Car car) {
        car.setId(++maxId);
        database.put(maxId, car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Car findById(Long id) {
        return database.get(id);
    }

    @Override
    public void deleteById(Long id) {

    }


}
