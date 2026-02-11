package app.controller;

import app.domain.Car;
import app.service.CarService;
import app.service.CarServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CarServlet extends HttpServlet {
    private final CarService service = new CarServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        super.doGet(req, resp);
        String id = req.getParameter("id");
        ObjectMapper mapper = new ObjectMapper();
        Writer writer = resp.getWriter();
        resp.setContentType("application/json");
        if (id == null) {
            List<Car> cars = service.getAll();
            mapper.writeValue(writer, cars);
        } else {
            Long numericId = Long.parseLong(id);
            Car car = service.getById(numericId);
            mapper.writeValue(writer, car);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.readValue(req.getReader(), Car.class);
        Car savedCar = service.save(car);
        resp.setContentType("Application/JSON");
        mapper.writeValue(resp.getWriter(), savedCar);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        Long numericId = Long.parseLong(id);
        service.deleteById(numericId);
    }
}
