package com.bykenyodarz.javamapeo.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<E, T> {


    Optional<E> getOne(T id);

    List<E> getAll();

    void save(E entity);

    void updateOne(E entity);

    void delete(E entity);

}
