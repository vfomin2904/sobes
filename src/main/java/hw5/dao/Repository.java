package hw5.dao;

public interface Repository <T>{

    void update(T entity);
    void insert(T entity);
    void delete(T entity);
    T findById(int id);
}
