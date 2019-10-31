package ru.user.lab04.servletapp.mysql;

import ru.user.lab04.servletapp.dao.AbstractDAO;
import ru.user.lab04.servletapp.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class GroupDAO extends AbstractDAO<Group> {

    public GroupDAO(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, name, description FROM mavendb.usergroups";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mavendb.usergroups (name, description) VALUES (?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mavendb.usergroups SET name = ?, description = ? WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mavendb.usergroups WHERE id = ?";
    }

    @Override
    public String getSearchQuery() {
        return "SELECT id, name, description FROM mavendb.usergroups WHERE";
    }

    @Override
    protected List<Group> parseResultSet(ResultSet rs) {
        LinkedList<Group> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName(rs.getString("name"));
                group.setDescription(rs.getString("description"));
                result.add(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Group group) {
        try {
            statement.setString(1, group.getName());
            statement.setString(2, group.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Group group) {
        try {
            statement.setString(1, group.getName());
            statement.setString(2, group.getDescription());
            statement.setInt(3, group.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}