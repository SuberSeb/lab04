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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/showUsers")
public class ShowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = null;
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");

        try (Connection connection = MySqlConnection.getConnection()) {
            UserDAO dao = new UserDAO(connection);
            users = dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/view/showUsers.jsp").forward(request, response);
    }
}
