package unit10.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Transaction;
import unit10.data.domain.CarsEntity;
import unit10.data.dto.CarDtoRequest;
import unit10.db.SessionFactorySingleton;
import unit10.repository.CarRepository;

@WebServlet(name = "carServlet", value = "/car/*")
public class CarServlet extends HttpServlet {

    private CarRepository carRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        carRepository = new CarRepository();
        objectMapper = new ObjectMapper();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            var carId = Integer.parseInt(request.getPathInfo().substring(1));
            var car = carRepository.findById(carId);

            String jsonString = objectMapper.writeValueAsString(car);
            if (car == null) {
                throw new FileNotFoundException("no car with such id");
            }
            tx.commit();
            response.setStatus(200);
            out.print(jsonString);
            out.flush();
        } catch (Throwable e) {
            if (e instanceof FileNotFoundException) {
                response.setStatus(404);
            } else if (e instanceof IOException) {
                response.setStatus(400);
            } else {
                response.setStatus(500);
            }
            tx.rollback();
            out.print(e);
            out.flush();
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            var carsDto = objectMapper.readValue(request.getReader(), CarDtoRequest.class);
            var carsEntity = CarsEntity.builder().brand(carsDto.getBrand()).number(carsDto.getNumber()).build();
            carRepository.save(carsEntity);
            tx.commit();
        } catch (Throwable e) {
            response.setStatus(400);
            tx.rollback();
            out.print(e);
            out.flush();
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            var carId = Integer.parseInt(request.getPathInfo().substring(1));
            var carsEnitity = carRepository.findById(carId);
            if (carsEnitity == null) {
                throw new FileNotFoundException("no car with such id");
            }
            var carsDto = objectMapper.readValue(request.getReader(), CarDtoRequest.class);
            carsEnitity.setBrand(carsDto.getBrand());
            carsEnitity.setNumber(carsDto.getNumber());
            carRepository.update(carsEnitity);
            tx.commit();
        } catch (Throwable e) {
            if (e instanceof FileNotFoundException) {
                response.setStatus(404);
            } else if (e instanceof IOException) {
                response.setStatus(400);
            } else {
                response.setStatus(500);
            }
            tx.rollback();
            out.print(e);
            out.flush();
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            var carId = Integer.parseInt(request.getPathInfo().substring(1));
            var car = carRepository.findById(carId);
            if (car == null) {
                throw new FileNotFoundException("no car with such id");
            }
            carRepository.delete(car);
            response.setStatus(200);
            tx.commit();
        } catch (Throwable e) {
            if (e instanceof FileNotFoundException) {
                response.setStatus(404);
            } else if (e instanceof IOException) {
                response.setStatus(400);
            } else {
                response.setStatus(500);
            }
            tx.rollback();
            out.print(e);
            out.flush();
        }
    }

    public void destroy() {
    }
}