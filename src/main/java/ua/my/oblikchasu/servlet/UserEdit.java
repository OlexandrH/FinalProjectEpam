package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.RequestDispatcher;
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
        //does nothing
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("id");
        String userPassword = request.getParameter("password");
        String userName = request.getParameter("name");
        if(userId != null) {
            UserService userService = new UserService();
            try {
                User user = userService.getById(Integer.parseInt(userId));
                user.setName(userName);
                user.setPassword(userPassword);
                userService.update(user);
                response.sendRedirect((String) request.getSession().getAttribute("return"));
            } catch (ServiceException | IOException e) {
                logger.error(LogMsg.ERROR, e);
                try {
                    response.sendRedirect("error.jsp");
                } catch (IOException ex) {
                    logger.error(LogMsg.ERROR, ex);
                }
            }
        }
    }
}
