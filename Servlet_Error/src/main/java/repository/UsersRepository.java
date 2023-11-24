package repository;



import models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    List findAllByAge(int i);

    List allUsers();

    String findDoctorByName(String name);

    String findUserByName(String name);


}
