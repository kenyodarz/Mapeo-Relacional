package com.bykenyodarz.javamapeo.dao;

import com.bykenyodarz.javamapeo.utils.EntityManagerUtil;
import lombok.Data;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Data
public abstract class AbstracDao<E, T> implements Dao<E, T> {

    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    private Class<E> clazz;

    @Override
    public Optional<E> getOne(T id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public List<E> getAll() {
        var qlString = "FROM " + clazz.getName();
        var query = entityManager.createQuery(qlString);
        return query.getResultList();
    }

    @Override
    public void save(E entity) {
        executeInsideTransaction(entityManager -> entityManager.persist(entity));
    }

    @Override
    public void updateOne(E entity) {
        executeInsideTransaction(entityManager -> entityManager.merge(entity));
    }

    @Override
    public void delete(E entity) {
        executeInsideTransaction(entityManager -> entityManager.remove(entity));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        var tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
