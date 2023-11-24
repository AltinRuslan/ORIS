package repository;




import models.User;
import repository.UsersRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    private Statement statement;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";
    private static final String SQL_SELECT_ALL_FROM_UUID = "select * from uuid";
    private static final String SQL_INSERT_INTO_USERS = "insert into driver(login,password,first_name,last_name) values ";


    public UsersRepositoryJdbcImpl(Connection connection) {
//        this.statement = statement;
        this.connection = connection;
    }
    @Override
    public void save(User entity) {

    }
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean findUser(String email, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);
            while (resultSet.next()) {
                User user = User.builder()
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
                if (user.getPassword().equals(password)){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean findByLogin(String email) {
            try {
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);

               while (resultSet.next()) {
                   User user = User.builder()
                           .email(resultSet.getString("email"))
                           .build();
                   if (user.getEmail().equals(email)) {
                       return true;
                   }
               }
               return false;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public String findIdByUUID(String uuid) {
        try (Statement statement = connection.createStatement();
             Statement statement1 = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_UUID);
            ResultSet resultSet1 = statement1.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);

            User copy = null;

            while (resultSet.next()) {

                User user = User.builder()
                        .id(resultSet.getString("id"))
                        .uuid(resultSet.getString("uuid"))
                        .build();
                if (user.getUuid().equals(uuid)) {
                    System.out.println("yes");
                    copy = user;
                }
            }
            System.out.println(copy);

            while (resultSet1.next()) {
                System.out.println(resultSet1.getString("id"));
                User user = User.builder()
                        .id(resultSet1.getString("id"))
                        .name(resultSet1.getString("first_name"))
                        .build();
                if (user.getId().equals(copy.getId())) {
                    return user.getName();
                }
            }
            return "0";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List findAllByAge(int num) {
        return null;
    }

    @Override
    public List allUsers() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from driver");
            System.out.println(resultSet);
            List<User> result = new ArrayList<>();


            while (resultSet.next()) {
                User user = User.builder()
                        .name(resultSet.getString("first_name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
                result.add(user);
                System.out.println(user.getName());
                if (result.isEmpty()) {
                    System.out.println("Пользователь не найден");
                }
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
