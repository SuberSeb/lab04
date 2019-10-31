package ru.user.lab04.servletapp.servlet.groups;

import ru.user.lab04.servletapp.model.Group;
import ru.user.lab04.servletapp.mysql.GroupDAO;
import ru.user.lab04.servletapp.mysql.MySqlConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/showGroups")
public class ShowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = null;
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");

        try (Connection connection = MySqlConnection.getConnection()) {
            GroupDAO dao = new GroupDAO(connection);
            groups = dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/view/showGroups.jsp").forward(request, response);
    }
}
