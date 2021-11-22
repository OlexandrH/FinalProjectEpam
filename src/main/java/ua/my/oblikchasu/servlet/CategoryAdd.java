package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.service.ActivityCategoryService;
import ua.my.oblikchasu.util.JavaScriptMessage;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category-add")
public class CategoryAdd extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CategoryAdd.class);
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name != null) {
           ActivityCategoryService categoryService = new ActivityCategoryService();
            try {
                ActivityCategory category = new ActivityCategory(0, name);

                if (categoryService.getActivityCategoryByName(name) == null) {
                    categoryService.addActivityCategory(category);
                    response.sendRedirect("category-list");
                } else {
                    JavaScriptMessage.alert(response.getWriter(), "Activity category already exists", "category-list" );
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
