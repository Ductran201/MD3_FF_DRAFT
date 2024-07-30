package ra.service;

import java.util.List;

public interface IGenericService<T,E,R> {
    List<T> findAll();
    T findById(E id);
    void save(R r);
    void delete(E id);

}
