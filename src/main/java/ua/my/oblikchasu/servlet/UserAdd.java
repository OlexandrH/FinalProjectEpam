package ua.my.oblikchasu.servlet;

import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.util.JavaScriptMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-add")
public class UserAdd extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String userName = request.getParameter("name");
        if (userLogin != null && userPassword != null && userName != null) {
            UserService userService = new UserService();
            User user = new User(0, userLogin, userPassword, UserRole.USER, userName);
            try {
                if (userService.getUserByLogin(userLogin) == null) {
                    user = userService.addUser(user);
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("user-page");
                } else {
                    JavaScriptMessage.alert(response.getWriter(), "User already exists", "register.jsp" );
                }
            } catch (ServiceException | IOException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp.html");
            }
        } else {
            response.sendRedirect("error.jsp.html");
        }
    }

}
