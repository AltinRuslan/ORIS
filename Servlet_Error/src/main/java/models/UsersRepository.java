package models;



import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    List findAllByAge();

    List allUsers();

}
