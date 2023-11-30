package servlets;

import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/admin")
public class addAdminServlet extends HttpServlet {
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
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            String sqlAddAdmin = "UPDATE driver SET role = 'admin' WHERE first_name = '" + name + "' AND email = '" + email + "'";

            int affectedRows = statement.executeUpdate(sqlAddAdmin);
            request.getRequestDispatcher("/admin/admin.html").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
