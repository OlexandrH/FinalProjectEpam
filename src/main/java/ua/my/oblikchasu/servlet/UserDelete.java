package ua.my.oblikchasu.servlet;

import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-delete")
public class UserDelete extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String userId = request.getParameter("id");

            if(userId != null) {
                UserService userService = new UserService();
                try {
                    userService.deleteUser(new User(Integer.parseInt(userId)));
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect("user-list");
    }

}
