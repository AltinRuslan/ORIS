package models;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    List<T> findAll();
    boolean findUser(String username, String password);
}
