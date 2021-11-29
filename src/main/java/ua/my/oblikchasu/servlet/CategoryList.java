package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.service.ActivityCategoryService;
import ua.my.oblikchasu.util.JavaScriptMessage;
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

@WebServlet("/category-list")
public class CategoryList extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CategoryList.class);
    private static final int pageSize = 5;
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
        String catSortBy = assignStringSessionAttribute(session,request, "categorySortBy");
        String catOrder = assignStringSessionAttribute(session, request, "categoryOrder");
        String categoryPage = request.getParameter("categoryPage");
        int catPage = (Integer)(session.getAttribute("categoryPage"));
        if(categoryPage != null) {
            catPage = Integer.parseInt(categoryPage);
            session.setAttribute("categoryPage", catPage);
        }
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();

        int count = activityCategoryService.getCount();
        int numPages = count/pageSize;
        if(count%pageSize != 0) {
            numPages++;
        }
        List <ActivityCategory> categories = activityCategoryService.getSortedPortion(catSortBy, (catPage-1)*pageSize, pageSize, catOrder);
        //List <ActivityCategory> categories = activityCategoryService.getAll();
                request.setAttribute("categories", categories);
                request.setAttribute("page", catPage);
                request.setAttribute("numPages", numPages);
                RequestDispatcher rd = request.getRequestDispatcher("category-list.jsp");
                rd.forward(request, response);
            } catch (ServiceException | ServletException | IOException e) {
                logger.error(LogMsg.ERROR, e);
                try {
                    response.sendRedirect("error.jsp");
                } catch (IOException ex) {
                    logger.error(LogMsg.ERROR, ex);
                }
            }
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {
        ActivityCategoryService activityCategoryService = new ActivityCategoryService();
        String param = request.getParameter("param");
        try {
            if ("add".equals(param)) {
                String name = request.getParameter("name");
                if(name != null) {
                    if(activityCategoryService.getByName(name) == null) {
                        ActivityCategory activityCategory = new ActivityCategory(0, name);
                        activityCategoryService.add(activityCategory);
                    } else {
                        JavaScriptMessage.alert(response.getWriter(), "Activity category already exists", "category-list.jsp" );
                    }
                }
            } else if ("update".equals(param)) {
                String name = request.getParameter("name");
                String id = request.getParameter("id");
                if (name != null && id != null) {
                    int categoryId = Integer.parseInt(id);
                    ActivityCategory activityCategory = activityCategoryService.getById(categoryId);
                    activityCategory.setName(name);
                    activityCategoryService.update(activityCategory);
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else if ("delete".equals(param)) {
                String id = request.getParameter("id");
                if (id != null) {
                    int categoryId = Integer.parseInt(id);
                    activityCategoryService.delete(new ActivityCategory(categoryId));
                }  else {
                    response.sendRedirect("error.jsp");
                }
            }
            response.sendRedirect("category-list");
        } catch (IOException | ServiceException | NumberFormatException e) {
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * If specified parameter of request is not null it's value is stored in the session attribute with the same name and returned
     */
    private String assignStringSessionAttribute (HttpSession session, HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if(param != null) {
            session.setAttribute(name, param);
        }
        return (String)(session.getAttribute(name));
    }
}
