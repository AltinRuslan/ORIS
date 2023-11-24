package listener;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "adidas19375";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_users_oris";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

//        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
//        servletContext.setAttribute("userRep", usersRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
