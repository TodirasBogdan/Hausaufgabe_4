package repository;

import java.util.List;

/**
 * declare create, getAll, update, delete functions
 *
 * @param <T>
 */
public interface ICrudRepository<T> {

    T create(T obj);

    List<T> getAll();

    T update(T obj);

    void delete(T obj);
}
