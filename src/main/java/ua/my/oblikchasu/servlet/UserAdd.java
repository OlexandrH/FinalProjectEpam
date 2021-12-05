package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.exception.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.util.LogMsg;
import ua.my.oblikchasu.util.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user-add")
public class UserAdd extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserAdd.class);
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect("register.jsp");
        } catch (IOException e) {
            logger.error(LogMsg.ERROR, e);
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String userName = request.getParameter("name");
        String errorMsg = "";
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");

        if (Validator.validateLogin(userLogin) && Validator.validatePassword(userPassword) && Validator.validateUserName(userName)) {
            UserService userService = new UserService();
            User user = new User(0, userLogin, userPassword, UserRole.USER, userName);
            try {
                if (userService.getByLogin(userLogin) == null) {
                    HttpSession session = request.getSession();
                    user = userService.add(user);
                    session.setAttribute("user", user);
                    session.setAttribute("return", "user-page");
                    response.sendRedirect((String)session.getAttribute("return"));
                } else {
                    errorMsg = "error.user_exists";
                    request.setAttribute("errorMsg", errorMsg);
                    rd.forward(request, response);
                }
            } catch (ServiceException | IOException e) {
                logger.error(LogMsg.ERROR, e);
                try {
                    response.sendRedirect("error.jsp");
                } catch (IOException ex) {
                    logger.error(LogMsg.ERROR, ex);
                }
            }
        } else {

            if(!Validator.validateUserName(userName)) {
                errorMsg = "error.wrong_username";
            }

            if(!Validator.validatePassword(userPassword)) {
                errorMsg = "error.wrong_password";
            }

            if(!Validator.validateLogin(userLogin)) {
                errorMsg = "error.wrong_login";
            }

            try {
                request.setAttribute("errorMsg", errorMsg);
                rd.forward(request, response);
            } catch (IOException | ServletException ex) {
            logger.error(LogMsg.ERROR, ex);
            }
        }
    }

}
