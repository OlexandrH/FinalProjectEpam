package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.exception.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user-delete")
public class UserDelete extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserDelete.class);

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //does nothing
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("id");
        try {
            if (userId != null) {
                UserService userService = new UserService();
                userService.delete(new User(Integer.parseInt(userId)));
                request.getSession().setAttribute("editUser", request.getSession().getAttribute("user"));
                response.sendRedirect("user-list");
            }
        } catch (ServiceException | IOException e) {
            try {
                logger.error(LogMsg.ERROR, e);
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR, ex);
            }
        }

    }

}
