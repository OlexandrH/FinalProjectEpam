package ua.my.oblikchasu.servlet;


import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.service.ActivityCategoryService;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.service.ActivityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import ua.my.oblikchasu.util.JavaScriptMessage;
import ua.my.oblikchasu.util.LogMsg;

@WebServlet("/activity-list")
public class ActivityList extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ActivityList.class);
    int categoryId=0;


    /**
     * Gets all activities from category cat-id and sends it to activity-list page
     */
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String catId = request.getParameter("cat-id");

        if(catId !=null) {
            try {
                categoryId = Integer.parseInt(catId);
                List<Activity> activities = new ActivityService().getByCategory(categoryId);
                List<ActivityCategory> categories = new ActivityCategoryService().getAll();

                request.setAttribute("activities", activities);
                request.setAttribute("categories", categories);
                RequestDispatcher rd = request.getRequestDispatcher("activity-list.jsp");
                rd.forward(request, response);
            } catch (ServiceException | IOException | ServletException e) {
                logger.error(LogMsg.ERROR, e);
                try {
                    response.sendRedirect("error.jsp");
                } catch (IOException ex) {
                    logger.error(LogMsg.ERROR, e);
                }
            }
        } else {
            try {
                response.sendRedirect("activity-list.jsp");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR, ex);
            }
        }
    }

    /**
     * Adds, updates or deletes activity depending on the value of the param value of request
    */
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            String id = request.getParameter("id");
            String param = request.getParameter("param");
            ActivityService activityService = new ActivityService();
            if (id != null) {

                int actId = Integer.parseInt(id);
                if ("delete".equals(param)) {
                    activityService.delete(new Activity(actId));
                } else if ("update".equals(param)) {
                    String name = request.getParameter("name");
                    int catId = Integer.parseInt(request.getParameter("cat-id"));
                    Activity activity = activityService.getById(actId);
                    activity.setName(name);
                    activity.setCategory(new ActivityCategory(catId));
                    activityService.update(activity);
                }
            } else if ("add".equals(param)) {
                System.out.println("add");
                String name = request.getParameter("name");
                String catId = request.getParameter("cat-id");

                if (name != null && catId != null) {
                    if (activityService.getByName(name) == null) {
                        Activity activity = new Activity(0, name, new ActivityCategory(Integer.parseInt(catId), ""));
                        activityService.add(activity);
                    } else {
                        JavaScriptMessage.alert(response.getWriter(), "Activity already exists", "category-list");
                    }
                }
            }

            response.sendRedirect("activity-list?cat-id=" + categoryId);

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
