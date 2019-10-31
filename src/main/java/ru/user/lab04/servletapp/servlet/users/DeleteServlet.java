package ru.user.lab04.servletapp.servlet.users;

import ru.user.lab04.servletapp.model.User;
import ru.user.lab04.servletapp.mysql.MySqlConnection;
import ru.user.lab04.servletapp.mysql.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/deleteUser")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            try (Connection connection = MySqlConnection.getConnection()) {
                UserDAO dao = new UserDAO(connection);
                User user = dao.getById(id);
                dao.delete(user);
            }

            response.sendRedirect(request.getContextPath() + "/showUsers");
        }
        catch (Exception e) {
            getServletContext().getRequestDispatcher("/notFound").forward(request, response);
        }
    }
}
