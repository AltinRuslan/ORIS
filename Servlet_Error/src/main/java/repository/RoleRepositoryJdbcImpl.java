package repository;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RoleRepositoryJdbcImpl implements RoleRepository{
    private Connection connection;
    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";
    private static final String SQL_SELECT_ALL_FROM_UUID = "select * from uuid";

    public RoleRepositoryJdbcImpl(Connection connection) {
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
    public String findRoleByEmailAndPassword(String password, String email) {
        String role = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);
            while (resultSet.next()) {
                User user = User.builder()
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getString("role"))
                        .build();
                if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                    role = user.getRole();
                }
            }
            return role;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
