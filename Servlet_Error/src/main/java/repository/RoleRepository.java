package repository;

import models.User;

public interface RoleRepository extends CrudRepository<User>{
    String findRoleByEmailAndPassword(String password, String email);
}
