package unit10.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.hibernate.Transaction;
import unit10.data.domain.AddressEntity;
import unit10.data.domain.CarsEntity;
import unit10.data.domain.UsersEntity;
import unit10.data.dto.CarDtoRequest;
import unit10.data.dto.UserCarDto;
import unit10.data.dto.UsersDto;
import unit10.db.SessionFactorySingleton;
import unit10.repository.AddressRepository;
import unit10.repository.CarRepository;
import unit10.repository.UserRepository;

@WebServlet(name = "userCarsServlet", value = "/user-car/*")
public class UserCarsServlet extends HttpServlet {

    private ObjectMapper objectMapper;
    private UserRepository userRepository;
    private CarRepository carRepository;

    @Override
    public void init() {
        objectMapper = new ObjectMapper();
        carRepository = new CarRepository();
        userRepository = new UserRepository();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            var userId = Integer.parseInt(request.getPathInfo().substring(1));
            var user = userRepository.findById(userId);
            if (user != null) {
                response.setStatus(200);
            } else {
                response.setStatus(404);
                throw new FileNotFoundException("User not found");
            }
            String jsonString = objectMapper.writeValueAsString(user.getCars());

            out.print(jsonString);
            out.flush();
            tx.commit();
        } catch (Throwable e) {
            tx.rollback();
            response.setStatus(400);
            out.println(e);
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
            var userCarDto = objectMapper.readValue(request.getReader(), UserCarDto.class);
            var user = userRepository.findById(userCarDto.getUserId());
            var car = carRepository.findById(userCarDto.getCarId());
            if (user == null) {
                throw new FileNotFoundException("User not found");
            }
            if (car == null) {
                throw new FileNotFoundException("Car not found");
            }
            user.addCar(car);
            userRepository.update(user);
            response.setStatus(200);
            tx.commit();
        } catch (Throwable e) {
            response.setStatus(400);
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
            var userCarDto = objectMapper.readValue(request.getReader(), UserCarDto.class);
            var user = userRepository.findById(userCarDto.getUserId());
            var car = carRepository.findById(userCarDto.getCarId());
            if (user == null) {
                throw new FileNotFoundException("User not found");
            }
            if (car == null) {
                throw new FileNotFoundException("Car not found");
            }
            user.removeCar(car);
            userRepository.update(user);
            response.setStatus(200);
            tx.commit();
        } catch (Exception e) {
            response.setStatus(400);
            out.println(e);
            tx.rollback();
        }
    }
}