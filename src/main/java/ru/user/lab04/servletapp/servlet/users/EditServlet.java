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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/editUser")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            try (Connection connection = MySqlConnection.getConnection()) {
                UserDAO dao = new UserDAO(connection);
                User user = dao.getById(id);
                request.setAttribute("user", user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            getServletContext().getRequestDispatcher("/WEB-INF/view/editUser.jsp").forward(request, response);
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("notFound").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthDate"));
            String email = request.getParameter("email");
            String city = request.getParameter("city");
            int group = Integer.parseInt(request.getParameter("group"));

            User user = new User(id, name, birthDate, email, city, group);

            try (Connection connection = MySqlConnection.getConnection()) {
                UserDAO dao = new UserDAO(connection);
                dao.update(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/showUsers");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/WEB-INF/view/notFound.jsp").forward(request, response);
        }
    }
}
