package ua.my.oblikchasu.servlet;

import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.ActivityCategory;
import ua.my.oblikchasu.service.ActivityCategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category-list")
public class CategoryList extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                List <ActivityCategory> categories = new ActivityCategoryService().getAllActivityCategories();
                request.setAttribute("categories", categories);
                RequestDispatcher rd = request.getRequestDispatcher("category-list.jsp");
                rd.forward(request, response);
            } catch (ServiceException e) {
                e.printStackTrace();
            }

    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String catId = request.getParameter("id");
        if(catId != null) {
            ActivityCategoryService activityCategoryService = new ActivityCategoryService();
            try {
                activityCategoryService.deleteActivityCategory(new ActivityCategory(Integer.parseInt(catId), ""));
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("category-list");
    }
}
