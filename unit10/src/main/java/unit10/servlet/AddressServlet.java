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
import unit10.data.domain.UsersEntity;
import unit10.data.dto.AddressDto;
import unit10.data.dto.UsersDto;
import unit10.db.SessionFactorySingleton;
import unit10.repository.AddressRepository;
import unit10.repository.UserRepository;

@WebServlet(name = "addressServlet", value = "/address/*")
public class AddressServlet extends HttpServlet {

    private ObjectMapper objectMapper;
    private AddressRepository addressRepository;

    @Override
    public void init() {
        objectMapper = new ObjectMapper();
        addressRepository = new AddressRepository();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        var session = SessionFactorySingleton.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {
            var addressId = Integer.parseInt(request.getPathInfo().substring(1));
            var address = addressRepository.findById(addressId);

            String jsonString = objectMapper.writeValueAsString(address);
            if (address != null) {
                response.setStatus(200);
            } else {
                throw new FileNotFoundException("No Adress with such id");
            }
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
            var addressDto = objectMapper.readValue(request.getReader(), AddressDto.class);
            var addressEntity = AddressEntity.builder().address(addressDto.getAddress()).build();
            addressRepository.save(addressEntity);
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
            var addressId = Integer.parseInt(request.getPathInfo().substring(1));

            var addressDto = objectMapper.readValue(request.getReader(), AddressDto.class);

            var addressEntity = addressRepository.findById(addressId);
            if (addressEntity == null) {
                throw new FileNotFoundException("Not found address with such id");
            }
            addressEntity.setAddress(addressDto.getAddress());
            addressRepository.update(addressEntity);
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
            var addressId = Integer.parseInt(request.getPathInfo().substring(1));
            var address = addressRepository.findById(addressId);
            if (address == null) {
                throw new FileNotFoundException("Not found address with such id");
            }
            addressRepository.delete(address);
            response.setStatus(200);
            tx.commit();
        } catch (Exception e) {
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