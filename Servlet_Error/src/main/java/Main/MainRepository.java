package Main;

import repository.UsersRepository;
import repository.UsersRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainRepository {
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "adidas19375";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db_users_oris";

    public static void main(String[] args) throws Exception{
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        UsersRepository userRepository = new UsersRepositoryJdbcImpl(connection);
        boolean users = userRepository.findByLogin("anton@gmail.c");
        System.out.println(users);
    }
}
