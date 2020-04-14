package jpa.api.api;

import jpa.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class IGenericDaoImpl<T extends Serializable> implements IGenericDao<T> {

    @PersistenceContext
    protected EntityManager manager;

    private Class<T> clazz;

    public IGenericDaoImpl() {
        manager = EntityManagerHelper.getEntityManager();
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        clazz = (Class) pt.getActualTypeArguments()[0];
    }


    public void save(final T entity) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(entity);
        transaction.commit();
    }


    public List<T> findAll() {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        List<T> list = manager.createQuery("from " + clazz.getName())
                .getResultList();
        transaction.commit();
        return list;
    }

    public void remove(T entity) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.remove(manager.merge(entity));
        transaction.commit();
    }

    public T update(T entity) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        T entityT = manager.merge(entity);
        transaction.commit();
        return entityT;
    }

    public T findById(long id) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        T entityT = manager.find(clazz, id);
        transaction.commit();
        return entityT;
    }

    public Class<T> getClazz() {
        return clazz;
    }

}

