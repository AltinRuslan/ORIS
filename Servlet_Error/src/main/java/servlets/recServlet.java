package servlets;

import repository.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/rec")
public class recServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "adidas19375";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_users_oris";
    private static final java.util.UUID  UUID = null;
    private static final String SQL_INSERT_INTO_TO_RECORD = "INSERT INTO record (id_user, id_doctor, date, time, email) VALUES (?, ?, ?, ?, ?)";
    private UsersRepository usersRepository;
    private DoctorRepository doctorRepository;
    private RecordRepository recordRepository;


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
            recordRepository = new RecordRepositoryJdbcImpl(connection);
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

            PrintWriter printWriter = response.getWriter();

            if (doctorId != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_TO_RECORD)){
                    if (recordRepository.timeIsExist(date, time)) {
                        preparedStatement.setString(1, userId);
                        preparedStatement.setString(2, doctorId);
                        preparedStatement.setString(3, date);
                        preparedStatement.setString(4, time);
                        preparedStatement.setString(5, email);

                        preparedStatement.executeUpdate();
                    } else {
                        System.out.println("Время занято");
                        printWriter.println("<h1>Время уже занято, вернитесь назад</h1>");
                    }
                }
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
