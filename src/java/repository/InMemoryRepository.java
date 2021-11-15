package repository;

import java.util.ArrayList;
import java.util.List;

/**
 * implement create, getAll, delete functions from ICrudRepository
 *
 * @param <T>
 */
public abstract class InMemoryRepository<T> implements ICrudRepository<T> {

    protected List<T> repoList;

    public InMemoryRepository() {
        this.repoList = new ArrayList<>();
    }

    @Override
    public T create(T obj) {
        this.repoList.add(obj);
        return obj;
    }

    @Override
    public List<T> getAll() {
        return this.repoList;
    }

    @Override
    public void delete(T obj) {
        this.repoList.remove(obj);
    }

}