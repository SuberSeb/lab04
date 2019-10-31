package ru.user.lab04.servletapp.mysql;

import ru.user.lab04.servletapp.dao.AbstractDAO;
import ru.user.lab04.servletapp.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, name, birthdate, email, city, usergroup FROM mavendb.users";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mavendb.users (name, birthdate, email, city, usergroup) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mavendb.users SET name = ?, birthdate = ?, email = ?, city = ?, usergroup = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mavendb.users WHERE `id` = ?";
    }

    @Override
    public String getSearchQuery() {
        return "SELECT id, name, birthdate, email, city, usergroup FROM mavendb.users WHERE";
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) {
        LinkedList<User> result = new LinkedList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setBirthDate(rs.getDate("birthdate"));
                user.setEmail(rs.getString("email"));
                user.setCity(rs.getString("city"));
                user.setGroup(rs.getInt("usergroup"));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User user) {
        try {
            Date sqlDate = convert(user.getBirthDate());

            statement.setString(1, user.getName());
            statement.setDate(2, sqlDate);
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCity());
            statement.setInt(5, user.getGroup());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User user) {
        try {
            Date sqlDate = convert(user.getBirthDate());

            statement.setString(1, user.getName());
            statement.setDate(2, sqlDate);
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCity());
            statement.setInt(5, user.getGroup());
            statement.setInt(6, user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
}
