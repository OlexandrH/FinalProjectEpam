package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.exception.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.util.LogMsg;
import ua.my.oblikchasu.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-edit")
public class UserEdit extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserEdit.class);
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        try {
            doPost(request, response);
        }
        catch (IOException | ServletException e) {
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR, ex);
            }
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("id");
        String userPassword = request.getParameter("password");
        String userName = request.getParameter("name");

        try {
            if (userId != null) {
                if(Validator.validateUserName(userName) && Validator.validatePassword(userPassword)) {
                    UserService userService = new UserService();
                    User user = userService.getById(Integer.parseInt(userId));
                    user.setName(userName);
                    user.setPassword(userPassword);
                    userService.update(user);
                    response.sendRedirect((String) request.getSession().getAttribute("return"));
                } else {
                    String errorMsg="";
                    if(!Validator.validateUserName(userName)) {
                        errorMsg = "error.wrong_username";
                    }
                    if(!Validator.validatePassword(userPassword)) {
                        errorMsg = "error.wrong_password";
                    }
                    request.setAttribute("errorMsg", errorMsg);
                    request.getRequestDispatcher((String)(request.getSession().getAttribute("return"))).forward(request, response);
                }
            } else {
                try {
                    response.sendRedirect("error.jsp");
                } catch (IOException ex) {
                    logger.error(LogMsg.ERROR, ex);
                }
            }
        } catch (ServletException | ServiceException | IOException e) {
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR, ex);
            }
        }
    }
}
