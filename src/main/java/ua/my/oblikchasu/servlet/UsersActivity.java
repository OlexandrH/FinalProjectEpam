package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.entity.*;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.service.UsersActivityService;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user-act-add")
public class UsersActivity extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UsersActivity.class);
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {
            UsersActivityService usersActivityService = new UsersActivityService();

            if(request.getParameter("delete")!=null) {
                 ua.my.oblikchasu.db.entity.UsersActivity usersActivity = usersActivityService.getById(Integer.parseInt(request.getParameter("id")));
                 usersActivityService.delete(usersActivity);
                 response.sendRedirect("user-list");
            }
            else {
                int userId = Integer.parseInt(request.getParameter("userId"));
                int activityId = Integer.parseInt(request.getParameter("activityId"));
                int timeHour = Integer.parseInt(request.getParameter("hour"));
                int timeMin = Integer.parseInt(request.getParameter("min"));

                ua.my.oblikchasu.db.entity.UsersActivity usersActivity = new ua.my.oblikchasu.db.entity.UsersActivity(0, new User(userId), new Activity(activityId), timeHour * 60 + timeMin, UsersActivityStatus.BOOKED);
                usersActivityService.add(usersActivity);
                response.sendRedirect("user-page");
            }

        } catch (NumberFormatException | ServiceException | IOException e) {
            logger.error(LogMsg.ERROR);
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR);
            }
        }
    }

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {

        try {
            if(request.getParameter("id") != null && request.getParameter("status") != null) {
                int usersActivityId = Integer.parseInt(request.getParameter("id"));
                int usersActivityStatus = Integer.parseInt(request.getParameter("status"));
                UsersActivityStatus status = UsersActivityStatus.values()[usersActivityStatus];
                UsersActivityService usersActivityService = new UsersActivityService();
                ua.my.oblikchasu.db.entity.UsersActivity usersActivity = usersActivityService.getById(usersActivityId);
                usersActivity.setStatus(status);
                usersActivityService.update(usersActivity);
            }
            if(((User)(request.getSession().getAttribute("user"))).getRole() == UserRole.ADMIN) {
                response.sendRedirect("user-list");
            } else {
                response.sendRedirect((String) (request.getSession().getAttribute("return")));
            }

        } catch (NumberFormatException | IOException | ServiceException /*| ServletException*/ e) {
            logger.error(LogMsg.ERROR, e);
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR, ex);
            }
        }

    }

}
