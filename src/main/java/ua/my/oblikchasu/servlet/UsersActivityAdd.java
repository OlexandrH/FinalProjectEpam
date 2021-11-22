package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UsersActivity;
import ua.my.oblikchasu.db.entity.UsersActivityStatus;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.service.UsersActivityService;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/user-act-add")
public class UsersActivityAdd extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UsersActivityAdd.class);
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int activityId = Integer.parseInt(request.getParameter("activityId"));
            int timeHour = Integer.parseInt(request.getParameter("hour"));
            int timeMin = Integer.parseInt(request.getParameter("min"));
            UsersActivityService usersActivityService = new UsersActivityService();
            UsersActivity usersActivity = new UsersActivity(0,new User(userId), new Activity(activityId), timeHour*60 + timeMin, UsersActivityStatus.BOOKED);
            usersActivityService.addUsersActivity(usersActivity);
            response.sendRedirect("user-page");
        } catch (NumberFormatException | ServiceException | IOException e) {
            logger.error(LogMsg.ERROR);
            try {
                response.sendRedirect("error.jsp.html");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR);
            }
        }
    }

    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        try {
            int usersActivityId = Integer.parseInt(request.getParameter("id"));
            UsersActivityService usersActivityService = new UsersActivityService();
            UsersActivity usersActivity =usersActivityService.getUsersActivityById(usersActivityId);
            usersActivity.setStatus(UsersActivityStatus.UNBOOKED);
            usersActivityService.updateUsersActivity(usersActivity);
            RequestDispatcher rd = request.getRequestDispatcher("user-page");
            rd.forward(request, response);
        } catch (NumberFormatException | IOException | ServiceException | ServletException e) {
            logger.error(LogMsg.ERROR);
            try {
                response.sendRedirect("error.jsp.html");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR);
            }
        }

    }

}
