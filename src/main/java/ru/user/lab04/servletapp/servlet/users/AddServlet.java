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

@WebServlet("/addUser")
public class AddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException, ServletException {
        try {
            String name = request.getParameter("name");
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthDate"));
            String email = request.getParameter("email");
            String city = request.getParameter("city");
            int group = Integer.parseInt(request.getParameter("group"));

            User user = new User(name, birthDate, email, city, group);

            try (Connection connection = MySqlConnection.getConnection()) {
                UserDAO dao = new UserDAO(connection);
                dao.insert(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/showUsers");
        } catch (Exception e) {
            getServletContext().getRequestDispatcher("/WEB-INF/view/addUser.jsp").forward(request, response);
        }
    }
}
