package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signout")
public class SignOut extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SignOut.class);
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession() != null) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                logger.info(user.getLogin() + LogMsg.SIGNED_OUT);
                request.getSession().invalidate();
            }
        }
        try {
            response.sendRedirect("index.jsp");
        } catch (IOException e) {
            logger.info(LogMsg.ERROR, e);
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        //does nothing
    }

}
