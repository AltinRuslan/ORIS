package repository;



import models.Record;
import models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    List findAllByAge(int i);

    List allUsers();


    String findUserByName(String name);

    String findUserByEmail(String email);
}
