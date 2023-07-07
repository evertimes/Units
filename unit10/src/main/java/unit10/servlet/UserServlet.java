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
import unit10.data.dto.UsersDto;
import unit10.db.SessionFactorySingleton;
import unit10.repository.AddressRepository;
import unit10.repository.CarRepository;
import unit10.repository.UserRepository;

@WebServlet(name = "userServlet", value = "/user/*")
public class UserServlet extends HttpServlet {

    private ObjectMapper objectMapper;
    private AddressRepository addressRepository;
    private UserRepository userRepository;

    @Override
    public void init() {
        objectMapper = new ObjectMapper();
        addressRepository = new AddressRepository();
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

            String jsonString = objectMapper.writeValueAsString(user);
            response.setContentType("application/json");
            if (user != null) {
                response.setStatus(200);
            } else {
                response.setStatus(404);
            }
            response.setCharacterEncoding("UTF-8");
            out.print(jsonString);
            out.flush();
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            var usersDto = objectMapper.readValue(request.getReader(), UsersDto.class);
            var addressEntity = addressRepository.findById(usersDto.getAddressId());
            var userEntityBuilder = UsersEntity.builder();
            if (addressEntity == null) {
                throw new FileNotFoundException("There is no address with such id");
            }
            userEntityBuilder.name(usersDto.getName()).addressId(addressEntity);
            var userEntity = userEntityBuilder.build();
            userRepository.save(userEntity);
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
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            var userId = Integer.parseInt(request.getPathInfo().substring(1));
            var usersDto = objectMapper.readValue(request.getReader(), UsersDto.class);
            var usersEntity = userRepository.findById(userId);
            if (usersEntity == null) {
                throw new FileNotFoundException("There is no user with such id");
            }
            var addressEntity = addressRepository.findById(usersDto.getAddressId());
            if (addressEntity == null) {
                throw new FileNotFoundException("There is no address with such id");
            }
            usersEntity.setName(usersDto.getName());
            usersEntity.setAddressId(addressEntity);
            userRepository.update(usersEntity);
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
            var userId = Integer.parseInt(request.getPathInfo().substring(1));
            userRepository.delete(userRepository.findById(userId));
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
}