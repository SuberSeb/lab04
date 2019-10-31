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

@WebServlet("/deleteGroup")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            try (Connection connection = MySqlConnection.getConnection()) {
                GroupDAO dao = new GroupDAO(connection);
                Group group = dao.getById(id);
                dao.delete(group);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/showGroups");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/notFound").forward(request, response);
        }
    }
}
