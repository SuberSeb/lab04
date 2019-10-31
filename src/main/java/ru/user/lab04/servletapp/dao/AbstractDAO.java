package ru.user.lab04.servletapp.dao;

import ru.user.lab04.servletapp.model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> implements Dao<T> {
    private final Connection connection;

    protected abstract String getSelectQuery();
    protected abstract String getCreateQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getSearchQuery();

    protected abstract List<T> parseResultSet(ResultSet rs);
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object);

    protected AbstractDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> getAll() throws SQLException {
        List<T> list;
        String query = getSelectQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        }
        return list;
    }

    @Override
    public List<T> search(String property, String value) throws SQLException {
        List<T> list;
        String query = getSearchQuery();
        query += " " + property + value;

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        }
        return list;
    }

    @Override
    public void update(T object) throws SQLException {
        String query = getUpdateQuery();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On update modify more then 1 record: " + count);
            }
        }
    }

    @Override
    public void delete(T object) throws SQLException {
        String query = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, object.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On delete modify more then 1 record: " + count);
            }
        }
    }

    @Override
    public void insert(T object) throws SQLException{
        String query = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new SQLException("On insert modify more then 1 record: " + count);
            }
        }
    }

    @Override
    public T getById(Integer id) throws SQLException {
        List<T> list;
        String query = getSelectQuery();
        query += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            list = parseResultSet(resultSet);
        }
        if (list == null || list.size() == 0) {
            throw new SQLException("Record with PK = " + id + " not found.");
        }
        if (list.size() > 1) {
            throw new SQLException("Received more than one record.");
        }
        return list.iterator().next();
    }
}
