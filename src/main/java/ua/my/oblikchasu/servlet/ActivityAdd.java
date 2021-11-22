package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.service.ActivityService;
import ua.my.oblikchasu.util.JavaScriptMessage;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activity-add")
public class ActivityAdd extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ActivityAdd.class);
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String catId = request.getParameter("cat-id");

        if (name != null && catId != null) {
            ActivityService activityService = new ActivityService();
            try {
                System.out.println(name);
                Activity activity = new Activity(0, name, new ActivityCategory(Integer.parseInt(catId), ""));
                System.out.println(activity);
                if (activityService.getActivityByName(name) == null) {
                    activity = activityService.addActivity(activity);

                    response.sendRedirect("activity-list?cat-id=" + catId);
                } else {
                    JavaScriptMessage.alert(response.getWriter(), "Activity already exists", "category-list" );
                }
            } catch (ServiceException | IOException e) {
                logger.error(LogMsg.ERROR);
                response.sendRedirect("error.jsp.html");
            }
        } else {
            response.sendRedirect("error.jsp.html");
        }
    }

}
