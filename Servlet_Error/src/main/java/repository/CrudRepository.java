package repository;

import java.util.List;

public interface CrudRepository<T> {
    void save(T entity);
    List<T> findAll();
    boolean findUser(String username, String password);
    boolean findByLogin(String email);

    String findIdByUUID(String id);
}
