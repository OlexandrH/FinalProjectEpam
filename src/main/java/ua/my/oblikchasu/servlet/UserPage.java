package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.UsersActivity;
import ua.my.oblikchasu.service.ActivityService;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.service.UsersActivityService;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/user-page")
public class UserPage extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserPage.class);

    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        final int pageSize = 5;
        HttpSession session = request.getSession();

        if(session == null || session.getAttribute("user") == null) {
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException e){
                logger.error(e);
            }
            return;
        }

        User user = (User)session.getAttribute("user");

        UsersActivityService usersActivityService = new UsersActivityService();
        ActivityService activityService = new ActivityService();
        UserService userService = new UserService();
        List<UsersActivity> usersActivities = new LinkedList<>();
        List<Activity> activities = new LinkedList<>();
        try {
            user = userService.getById(user.getId());
            session.setAttribute("user", user);
            if (request.getParameter("usersActSortBy") != null) {
                session.setAttribute("usersActSortBy", request.getParameter("usersActSortBy"));
            }
            String sortBy = (String) session.getAttribute("usersActSortBy");

            if (request.getParameter("usersActOrder") != null) {
                session.setAttribute("usersActOrder", request.getParameter("usersActOrder"));
            }

            String order = (String) session.getAttribute("usersActOrder");

            if (request.getParameter("usersActPage") != null) {
                session.setAttribute("usersActPage", Integer.parseInt(request.getParameter("usersActPage")));
            }
            int page = (Integer) session.getAttribute("usersActPage");
            int count = usersActivityService.getCountByUser(user);
            int numberPages = count / pageSize;
            if (count % pageSize != 0) {
                numberPages++;
            }
            usersActivities = usersActivityService.getPortionByUser(user, sortBy, (page - 1) * pageSize, pageSize, order);
            activities = activityService.getAll();
            request.setAttribute("numPages", numberPages);
            request.setAttribute("usersActivities", usersActivities);
            request.setAttribute("activities", activities);
            request.setAttribute("usersActPage", null);
            RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
            rd.forward(request, response);
        } catch (ServiceException | ServletException | IOException e) {
            logger.error(LogMsg.ERROR);
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ex ) {
                logger.error(LogMsg.ERROR);
            }
        }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        //does nothing
    }

}
