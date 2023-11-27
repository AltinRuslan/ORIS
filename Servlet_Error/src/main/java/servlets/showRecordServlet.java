package servlets;

import models.Record;
import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet("/profile")
public class showRecordServlet extends HttpServlet {
    private UsersRepository usersRepository;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(getUUID(request.getCookies()) != null) {
            String userName = usersRepository.findIdByUUID(getUUID(cookies));
            System.out.println(userName);
            String id = usersRepository.findUserByName(userName);
            System.out.println(id);
            List records = usersRepository.findAllRecords(id);
            if (records != null) {
                request.setAttribute("records", records);
            }
            request.getRequestDispatcher("jsp/profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("/recServlet");
        }
    }


    public String getUUID(Cookie[] cookie) {
        for (Cookie c : cookie ) {
            if(c.getName().equals("id")) {
                return c.getValue();
            }
        }
        return null;
    }
}
