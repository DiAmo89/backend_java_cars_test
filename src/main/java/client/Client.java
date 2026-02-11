package client;

import app.controller.CarController;
import app.domain.Car;

import java.math.BigDecimal;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        CarController controller = new CarController();
        // Имитация http-запроса
        Car savedCar = controller.save("Volkswagen", 2010, new BigDecimal(10000));
        System.out.println("Сохранённый автомобиль:");
        System.out.println(savedCar);

        savedCar = controller.save("Mazda", 2015, new BigDecimal(20000));
        System.out.println("Сохранённый автомобиль:");
        System.out.println(savedCar);

        System.out.println();

        List<Car> cars = controller.getAll();
        System.out.println("all cars");
        cars.forEach(System.out::println);
    }
}