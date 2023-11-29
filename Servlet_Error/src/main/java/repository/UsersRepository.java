package repository;



import models.Record;
import models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    List findAllByAge(int i);

    List allUsers();

    String findDoctorByName(String name);

    String findUserByName(String name);

    List<Record> findAllRecords(String id);

    String findDoctorById(String id);
    String findRoleByEmailAndPassword(String password, String email);

    String findUserByEmail(String email);
}
