package servlets;

import repository.DoctorRepository;
import repository.DoctorRepositoryJdbcImpl;
import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/rec")
public class recServlet extends HttpServlet {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "adidas19375";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_users_oris";
    private static final java.util.UUID  UUID = null;
    

    private UsersRepository usersRepository;
    private DoctorRepository doctorRepository;


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
            doctorRepository = new DoctorRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            String userName = request.getParameter("userName");
            String doctorName = request.getParameter("doctorName");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String email = request.getParameter("email");

            String userId = usersRepository.findUserByName(userName);
            String doctorId = doctorRepository.findDoctorByName(doctorName);

            if (doctorId != null) {
                String sqlInsertAppointment = "INSERT INTO record (id_user, id_doctor, date, time, email) " +
                        "VALUES ('" + userId + "', " + doctorId + ", '" + date + "', '" + time + "', '" + email + "')";
                System.out.println(sqlInsertAppointment);

                statement.executeUpdate(sqlInsertAppointment);

                response.sendRedirect("/profile");
            } else {
                response.getWriter().println("Врач с именем " + doctorName + " не найден.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/record.jsp").forward(request, response);
    }
}
