package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.Activity;
import ua.my.oblikchasu.service.ActivityService;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/activity-all")
public class ActivityAll extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ActivityAll.class);
    private static final int PAGE_SIZE = 7;
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            String actSortBy = assignStringSessionAttribute(session, request, "activitySortBy");
            String actOrder = assignStringSessionAttribute(session, request, "activityOrder");
            int actPage = assignIntSessionAttribute(session, request, "activityPage");
            ActivityService activityService = new ActivityService();
            List<Activity> activities = activityService.getAll();

            int count = activityService.getCount();
            int numberPages = count/ PAGE_SIZE;
            if(count% PAGE_SIZE != 0) {
                numberPages++;
            }


            activities = activityService.getSortedPortion(actSortBy, (actPage-1)* PAGE_SIZE, PAGE_SIZE, actOrder);

            request.setAttribute("page", actPage);
            request.setAttribute("numPages", numberPages);
            request.setAttribute("activities", activities);
            RequestDispatcher rd = request.getRequestDispatcher("activity-all.jsp");
            rd.forward(request, response);
        } catch (NumberFormatException | ServiceException | ServletException | IOException e) {
            logger.error(LogMsg.ERROR, e);
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR, ex);
            }
        }
    }


    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //does nothing
    }

    private String assignStringSessionAttribute (HttpSession session, HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if(param != null) {
            session.setAttribute(name, param);
        }
        return (String)(session.getAttribute(name));
    }

    private Integer assignIntSessionAttribute (HttpSession session, HttpServletRequest request, String name) throws NumberFormatException {
        String param = request.getParameter(name);
        if(param != null) {
            session.setAttribute(name, Integer.parseInt(param));
        }
        return (Integer)(session.getAttribute(name));
    }
}
