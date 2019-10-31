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

@WebServlet("/addGroup")
public class AddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/addGroup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException, ServletException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            Group group = new Group(name, description);

            try (Connection connection = MySqlConnection.getConnection()) {
                GroupDAO dao = new GroupDAO(connection);
                dao.insert(group);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/showGroups");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/WEB-INF/view/addGroup.jsp").forward(request, response);
        }
    }
}
