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

@WebServlet("/activity-edit")
public class ActivityEdit extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ActivityEdit.class);
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String name = request.getParameter("name");
//        String id = request.getParameter("id");
//
//        if (name != null && id != null) {
//
//            ActivityService activityService = new ActivityService();
//            try {
//                Activity activity = activityService.getActivityById();
//                System.out.println(activity);
//                if (activityService.getActivityByName(name) == null) {
//                    activity = activityService.addActivity(activity);
//
//                    response.sendRedirect("activity-list?cat-id=" + catId);
//
//                } else {
//                    JavaScriptMessage.alert(response.getWriter(), "Activity already exists", "category-list" );
//
//                }
//            } catch (ServiceException | IOException e) {
//                logger.error(LogMsg.ERROR);
//                response.sendRedirect("error.jsp.html");
//            }
//        } else {
//            response.sendRedirect("error.jsp.html");
//        }
    }

}
