package repository;

import models.Record;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecordRepositoryJdbcImpl implements RecordRepository{
    private Connection connection;
    private static final String SQL_SELECT_ALL_FROM_RECORDS = "select * from record";
    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";

    public RecordRepositoryJdbcImpl(Connection connection) {
//        this.statement = statement;
        this.connection = connection;
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
    public boolean timeIsExist(String date, String time) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_RECORDS);
            while (resultSet.next()) {
                Record record = Record.builder()
                        .date(resultSet.getString("date"))
                        .time(resultSet.getString("time"))
                        .build();
                if (record.getTime().equals(time) && record.getDate().equals(date)){
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Record entity) {

    }

    @Override
    public List<Record> findAll() {
        return null;
    }

    @Override
    public boolean findUser(String username, String password) {
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
        return false;
    }

    @Override
    public String findIdByUUID(String id) {
        return null;
    }
}
