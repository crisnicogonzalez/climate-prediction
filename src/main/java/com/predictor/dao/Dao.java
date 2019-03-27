package com.predictor.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T>{

    Optional<T> get(long id);

    Optional<T> save(T t);

    void delete(T t);

    void saveAll(List<T> elements);
}
