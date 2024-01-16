package unit10.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.hibernate.Transaction;
import unit10.data.dto.UserCarDto;
import unit10.db.SessionFactorySingleton;
import unit10.repository.CarRepository;
import unit10.repository.UserRepository;

@WebServlet(name = "redirectForwardServlet", value = "/redirect-forward/*")
public class RedirectForwardServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var method = request.getPathInfo().substring(1);
        if(method.equals("redirect")){
            response.sendRedirect("http://ya.ru");
        }else if(method.equals("forward")){
            request.getServletContext().getRequestDispatcher("/car/1").forward(request,response);
        }else {
            PrintWriter out = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            out.println("error");
            out.flush();
        }

    }
}