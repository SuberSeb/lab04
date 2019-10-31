package ru.user.lab04.servletapp.dao;

import ru.user.lab04.servletapp.model.Entity;

import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс для работы DAO
 * @param <T> Тип сущности
 */
interface Dao<T extends Entity> {
    void insert(T object) throws SQLException;
    void update(T object) throws SQLException;
    void delete(T object) throws  SQLException;
    T getById(Integer id) throws SQLException;
    List<T> getAll() throws SQLException;
    List<T> search(String property, String value) throws SQLException;
}