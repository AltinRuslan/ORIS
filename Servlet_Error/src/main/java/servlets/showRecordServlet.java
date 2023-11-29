package servlets;

import models.Record;
import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/profile")
public class showRecordServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "adidas19375";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_users_oris";
    private static final java.util.UUID  UUID = null;

    private UsersRepository usersRepository;


    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        HttpSession session = request.getSession();

        if (session.getAttribute("id") != null) {
            String isId = (String) session.getAttribute("id");
            System.out.println("в servlet зашел и увидел id " + isId);
            List result = usersRepository.findAllRecords(isId);


            if (!result.isEmpty()) {
                System.out.println("Он нашел записи");
                request.setAttribute("recordsForJsp", result);
                request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
            } else {
                printWriter.println("<h1>list is empty</h1>");
                System.out.println("Список пустой");
            }
        }
//        if(getUUID(request.getCookies()) != null) {
//            System.out.println(userName);
//            String id = usersRepository.findUserByName(userName);
//            System.out.println(id);
//            List records = usersRepository.findAllRecords(id);
//            if (records != null) {
//                request.setAttribute("records", records);
//            }
//            request.getRequestDispatcher("jsp/profile.jsp").forward(request, response);
//        } else {
//            response.sendRedirect("/recServlet");
//        }
    }



}
