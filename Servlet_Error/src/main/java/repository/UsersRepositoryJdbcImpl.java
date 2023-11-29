package repository;




import models.Doctor;
import models.Record;
import models.User;

import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    private Statement statement;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";
    private static final String SQL_SELECT_ALL_FROM_UUID = "select * from uuid";
    private static final String SQL_SELECT_ALL_FROM_DOCTORS = "select * from doctors";
    private static final String SQL_SELECT_ALL_FROM_RECORDS = "select * from record";


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

    @Override
    public String findDoctorByName(String doctorName) {
        String idDoctor = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DOCTORS);
            while (resultSet.next()) {
                Doctor doctor = Doctor.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("name"))
                        .build();
                if (doctor.getName().equals(doctorName)){
                    idDoctor = doctor.getId();
                }
            }
            return idDoctor;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public String findUserByName(String name) {
        String idUser = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("first_name"))
                        .build();
                if (user.getName().equals(name)){
                    idUser = user.getId();
                }
            }
            return idUser;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public String findUserByEmail(String email) {
        String idUser = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("first_name"))
                        .email(resultSet.getString("email"))
                        .build();
                if (user.getEmail().equals(email)){
                    idUser = user.getId();
                }
            }
            return idUser;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    @Override
    public List<Record> findAllRecords(String id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_RECORDS);
            List<Record> result = new ArrayList<>();
            System.out.println("Вот передаваемый id: " + id);

            while (resultSet.next()) {
                Record record = Record.builder()
                        .idUser(resultSet.getString("id_user"))
                        .idDoctor(resultSet.getString("id_doctor"))
                        .date(resultSet.getString("date"))
                        .time(resultSet.getString("time"))
                        .email(resultSet.getString("email"))
                        .build();
                if (record.getIdUser().equals(id)) {
                    System.out.println("id у них равные, будет добавлять");
                    result.add(record);
                } else {
                    System.out.println("id у них не равные, будет добавлять");
                }
            }
            if (result.isEmpty()) {
                System.out.println("По введенному возврасту ничего не найдено...");
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String findDoctorById(String id) {
        String NameDoctor = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DOCTORS);
            while (resultSet.next()) {
                Doctor doctor = Doctor.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("name"))
                        .build();
                if (doctor.getId().equals(id)){
                    NameDoctor = doctor.getName();
                }
            }
            return NameDoctor;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
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
