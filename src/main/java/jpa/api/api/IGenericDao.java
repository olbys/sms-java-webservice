package jpa.api.api;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao< T extends Serializable> {

    T findById( long id);

    List<T> findAll();

    void save(final T entity);

    T update(final T entity);

    void remove(final T entity);



}
