package ua.my.oblikchasu.servlet;

import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-edit")
public class UserEdit extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        if(userId != null) {
            UserService userService = new UserService();
            try {
                User user = userService.getUserById(Integer.parseInt(userId));
                request.setAttribute("user", user);
                RequestDispatcher rd = request.getRequestDispatcher("user-edit.jsp");
                rd.forward(request, response);
            } catch (ServiceException e) {
                response.sendRedirect("error.jsp.html");
            }
        }

    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        String userPassword = request.getParameter("password");
        String userName = request.getParameter("name");
        if(userId != null) {
            UserService userService = new UserService();
            try {
                User user = userService.getUserById(Integer.parseInt(userId));
                user.setName(userName);
                user.setPassword(userPassword);
                userService.updateUser(user);
                response.sendRedirect("user-list");
            } catch (ServiceException e) {
                response.sendRedirect("error.jsp.html");
            }
        }

    }

}
