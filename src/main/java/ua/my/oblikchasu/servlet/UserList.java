package ua.my.oblikchasu.servlet;

import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user-list")
public class UserList extends HttpServlet {
    public int page = 0;
    private static final int pageSize = 5;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if ("next".equals(request.getParameter("page"))) {
                page++;
            } else if ("prev".equals(request.getParameter("page"))) {
                if (page > 0) {
                    page--;
                }
            } else if ("first".equals(request.getParameter("page"))) {
               page=0;
        }
            List<User> users = new UserService().getSortedPortionUsers("id", page * pageSize, pageSize);
            request.setAttribute("userList", users);
            RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
            rd.forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
