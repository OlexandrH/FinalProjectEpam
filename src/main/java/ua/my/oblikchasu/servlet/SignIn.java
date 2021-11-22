package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.URL;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.util.LogMsg;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signin")
public class SignIn extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SignIn.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            try {
                if (user.getRole() == UserRole.USER) {
                    response.sendRedirect("user-page");
                } else if (user.getRole() == UserRole.ADMIN) {
                    response.sendRedirect("admin.jsp");
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            String userLogin = request.getParameter("userLogin");
            String userPassword = request.getParameter("userPassword");

            try {
                if(validateUser(userLogin, userPassword)) {
                    UserService userService = new UserService();
                    user = userService.getUserByLogin(userLogin);
                    session.setAttribute("user", user);
                    if (user.getRole() == UserRole.USER) {
                        response.sendRedirect("user-page");
                    } else if (user.getRole() == UserRole.ADMIN) {
                        response.sendRedirect("admin.jsp");
                    }
                    logger.info(userLogin + LogMsg.SIGNED_IN);
                } else {
                    request.getRequestDispatcher("/signin.jsp").forward(request, response);
                    logger.info(LogMsg.SPACE + userLogin + LogMsg.SPACE + LogMsg.ACCESS_DENIED);
                }
            } catch (ServiceException | IOException | ServletException e) {
                try {
                    response.sendRedirect(URL.ERROR);//add message
                } catch (IOException ex) {
                    logger.error(LogMsg.ERROR, ex);
                }
            }

        }
    }

    private boolean validateUser (String userLogin, String userPassword) throws ServiceException {
        UserService userService = new UserService();
        if(userLogin == null || userPassword == null) return false;
        User user = userService.getUserByLogin(userLogin);
        if(user == null) {
            return false;
        }
        return userPassword.equals(user.getPassword());
    }
}