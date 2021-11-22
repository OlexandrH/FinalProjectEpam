package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.UsersActivity;
import ua.my.oblikchasu.service.ActivityService;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/user-page")
public class UserPage extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserPage.class);
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("usersActSortBy"));
        System.out.println(session.getAttribute("usersActOrder"));
        System.out.println(session.getAttribute("usersActPage"));
        if(session == null || session.getAttribute("user") == null) {
            try {
                response.sendRedirect("error.jsp.html");
            } catch (IOException e){
                logger.error(e);
            }
            return;
        }

        User user = (User)session.getAttribute("user");

        UsersActivityService usersActivityService = new UsersActivityService();
        ActivityService activityService = new ActivityService();
        List<UsersActivity> usersActivities = new LinkedList<>();
        List<Activity> activities = new LinkedList<>();
        try {
            if(request.getParameter("usersActSortBy") != null) {
                session.setAttribute("usersActSortBy", request.getParameter("usersActSortBy"));
            }
            String sortBy = (String)session.getAttribute("usersActSortBy");

            if("asc".equals(session.getAttribute("usersActOrder"))) {
                session.setAttribute("usersActOrder", "desc");
            } else if("desc".equals(session.getAttribute("usersActOrder"))) {
                session.setAttribute("usersActOrder", "asc");
            }
            String order = (String)session.getAttribute("usersActOrder");

            if(request.getParameter("usersActPage") != null) {
                session.setAttribute("usersActPage", Integer.parseInt(request.getParameter("usersActPage")));
            }
            int page = (int) session.getAttribute("usersActPage");

            usersActivities = usersActivityService.getUsersActivitiesPortionByUser(user, sortBy,0, 5, order);
            activities = activityService.getAllActivities();
            request.setAttribute("usersActivities", usersActivities);
            request.setAttribute("activities", activities);
            RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
            rd.forward(request, response);
        } catch (ServiceException | ServletException e) {
            logger.error(LogMsg.ERROR);
            try {
                response.sendRedirect("error.jsp.html");
            } catch (IOException ex ) {
                logger.error(LogMsg.ERROR);
            }
        }


    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if(session == null || session.getAttribute("user") == null) {
            try {
                response.sendRedirect("error.jsp.html");
            } catch (IOException e){
                logger.error(e);
            }
            return;
        }

        String userId = request.getParameter("id");
        String userPassword = request.getParameter("password");
        String userName = request.getParameter("name");
        if(userId != null) {
            UserService userService = new UserService();
            try {
                User user = (User)request.getSession().getAttribute("user");
                user.setName(userName);
                user.setPassword(userPassword);
                userService.updateUser(user);
                response.sendRedirect("user-page");
            } catch (ServiceException | NumberFormatException | IOException e) {
                logger.error(LogMsg.ERROR);
                try {
                response.sendRedirect("error.jsp.html");
                } catch (IOException ex ) {
                    logger.error(LogMsg.ERROR);
                }
            }
        }
    }

}
