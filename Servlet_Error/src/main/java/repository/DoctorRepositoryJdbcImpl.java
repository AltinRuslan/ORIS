package repository;

import models.Doctor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DoctorRepositoryJdbcImpl implements DoctorRepository{
    private Connection connection;
    private static final String SQL_SELECT_ALL_FROM_DOCTORS = "select * from doctors";

    public DoctorRepositoryJdbcImpl(Connection connection) {
//        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public void save(Doctor entity) {

    }

    @Override
    public List<Doctor> findAll() {
        return null;
    }

    @Override
    public boolean findUser(String username, String password) {
        return false;
    }

    @Override
    public boolean findByLogin(String email) {
        return false;
    }

    @Override
    public String findIdByUUID(String id) {
        return null;
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
}
