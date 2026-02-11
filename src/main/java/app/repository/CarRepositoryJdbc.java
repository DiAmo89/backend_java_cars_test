package app.repository;

import app.constants.Constants;
import app.domain.Car;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static app.constants.Constants.*;

public class CarRepositoryJdbc implements CarRepository {

    private Connection getConnection() {
        try {
            Class.forName(DB_DRIVER_PATH);
            String dbURL = DB_URL + DB_NAME;
            return DriverManager.getConnection(dbURL, DB_USERNAME, DB_PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car save(Car car) {
        try (Connection connection = getConnection()) {


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Car> findAll() {
        try (Connection connection = getConnection()) {

            String query = "SELECT * FROM car;";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<Car> cars = new ArrayList<>();
            while (result.next()) {
                Long id = result.getLong("id");
                String brand = result.getString("brand");
                int year = result.getInt("year");
                BigDecimal price = result.getBigDecimal("price");

                Car car = new Car(id, brand, year, price);
                cars.add(car);
            }
            return cars;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Car findById(Long id) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
