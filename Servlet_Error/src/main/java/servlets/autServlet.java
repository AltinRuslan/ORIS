package servlets;

import repository.RoleRepository;
import repository.RoleRepositoryJdbcImpl;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;


@WebServlet("/aut")
public class autServlet extends HttpServlet {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "adidas19375";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_users_oris";

    private UsersRepository usersRepository;
    private RoleRepository roleRepository;

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
            roleRepository = new RoleRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(email);
//        String unUUID = UUID.randomUUID().toString();

        

        if(usersRepository.findUser(email, password)) {
            if (roleRepository.findRoleByEmailAndPassword(password, email).equals("admin")) {
                request.getRequestDispatcher("/admin/admin.html").forward(request, response);
            } else {
                System.out.println(email + " Он смог " + password);
                String id = usersRepository.findUserByEmail(email);
//            Cookie cookies = new Cookie("id", unUUID);
//            response.addCookie(cookies);
//            cookies.setMaxAge(3600 * 24);

//            session.setAttribute("isAMainPage", true);
                HttpSession session = request.getSession(true);
                session.setAttribute("authenticated", true);
                session.setAttribute("id", id);

                System.out.println("Он видит, что пользователь есть");
                response.sendRedirect("/main_page");
                System.out.println("отпавил на главную страницу");
            }

        } else {
            System.out.println(email + " Он не смог " + password);
            response.sendRedirect("/reg");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCookies() == null || getUUID(request.getCookies()) != null) {
            System.out.println("Не нашел куки");
            request.getRequestDispatcher("/html/index2.html").forward(request, response);
        } else {
            Cookie[] cookies = request.getCookies();
            if(getUUID(request.getCookies()) != null) {
                String userName = usersRepository.findIdByUUID(getUUID(cookies));
                if(!userName.equals("0")) {
                    request.getRequestDispatcher("/html/index2.html").forward(request, response);
                }
            }
            else {
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
