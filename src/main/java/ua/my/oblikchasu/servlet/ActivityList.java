package ua.my.oblikchasu.servlet;

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

@WebServlet("/activity-list")
public class ActivityList extends HttpServlet {
    int categoryId=0;
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String catId = request.getParameter("cat-id");

        if(catId !=null) {
            try {
                categoryId = Integer.parseInt(catId);
                List<Activity> activities = new ActivityService().getActivitiesByCategory(categoryId);
                request.setAttribute("activities", activities);
                //request.setAttribute("cat-id", catId);
               // response.sendRedirect("activity-list.jsp");
                 RequestDispatcher rd = request.getRequestDispatcher("activity-list.jsp");
                rd.forward(request, response);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("activity-list.jsp");
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id != null) {
            ActivityService activityService = new ActivityService();
            try {
                activityService.deleteActivity(new Activity(Integer.parseInt(id)));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("activity-list?cat-id=" + categoryId);
    }
}
