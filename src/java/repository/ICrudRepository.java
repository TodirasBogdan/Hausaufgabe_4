package repository;

import java.io.IOException;
import java.util.List;

/**
 * declare create, getAll, update, delete functions
 *
 * @param <T>
 */
public interface ICrudRepository<T> {

    T create(T obj) throws IOException;

    List<T> getAll() throws IOException;

    T update(T obj) throws IOException;

    void delete(T obj) throws IOException;
}
