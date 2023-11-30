package servlets;

import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.UUID;


@WebServlet("/reg")
public class regServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "adidas19375";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_users_oris";
    private static final String SQL_INSERT_INTO_TO_DRIVER = "INSERT INTO driver (first_name, email, password, role) VALUES (?, ?, ?, ?)";
    private static final String SQL_INSERT_INTO_TO_UUID = "INSERT INTO uuid (uuid) VALUES (?)";

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String lastPassword = request.getParameter("confirm_password");

//        System.out.println(firstName);
//        System.out.println(email);
//        System.out.println(password);
//        System.out.println(lastPassword);

        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            String id = usersRepository.findUserByName(firstName);
            String unUUID = UUID.randomUUID().toString();

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_TO_DRIVER)){
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, "User");

                int affectedRows = preparedStatement.executeUpdate();

                PrintWriter printWriter = response.getWriter();

                if(affectedRows > 0 && !password.isEmpty() && !firstName.isEmpty() && !email.isEmpty() && password.equals(lastPassword)) {

                    Cookie cookies = new Cookie("id", unUUID);
                    response.addCookie(cookies);

                    try (PreparedStatement uuidStatement = connection.prepareStatement(SQL_INSERT_INTO_TO_UUID)){
                        uuidStatement.setString(1, unUUID);
                        uuidStatement.executeUpdate();
                    }

                    HttpSession session = request.getSession(true);
                    session.setAttribute("authenticated", true);
                    session.setAttribute("id", id);

                    cookies.setMaxAge(3600 * 24);
                    response.sendRedirect("/main_page");
                } else {
                    printWriter.println("<h1>reg failed</h1>");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/index.html").forward(request, response);
    }
}
