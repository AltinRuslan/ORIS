package servlets;

import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;


@WebServlet("/aut")
public class autServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "adidas19375";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_users_oris";

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter writer = response.getWriter();

        if(usersRepository.findUser(email, password)) {
            writer.println("<h1>Log In</h1>");
        } else {
            writer.println("<h1>Failed</h1>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCookies() == null) {
            request.getRequestDispatcher("/html/index2.html").forward(request, response);
        } else {
            Cookie[] cookies = request.getCookies();
            String userName = usersRepository.findIdByUUID(getUUID(cookies));

            if(!userName.equals("0")) {
                System.out.println("В profile.html пошел");
                request.getRequestDispatcher("profile.html").forward(request, response);
            } else {
                System.out.println("В index2.html пошел");
                request.getRequestDispatcher("/html/index2.html").forward(request, response);
            }
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
