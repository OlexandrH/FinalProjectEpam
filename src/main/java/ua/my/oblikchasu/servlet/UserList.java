package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.service.ServiceException;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.service.UserService;
import ua.my.oblikchasu.service.UsersActivityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import ua.my.oblikchasu.db.entity.UsersActivity;
import ua.my.oblikchasu.util.LogMsg;

@WebServlet("/user-list")
public class UserList extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserList.class);
    private static final int pageSize = 5;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserService userService = new UserService();

        try {
            User editUser = (User)session.getAttribute("editUser");

            if (request.getParameter("editUserId") != null) {
                int editUserId = Integer.parseInt(request.getParameter("editUserId"));
                editUser = userService.getById(editUserId);
            }
            if (editUser == null) {
                editUser = user;
            }

            session.setAttribute("editUser", editUser);

            if (request.getParameter("userSortBy") != null) {
                session.setAttribute("userSortBy", request.getParameter("userSortBy"));
            }
            String userSortBy = (String) session.getAttribute("userSortBy");

            if (request.getParameter("userOrder") != null) {
                session.setAttribute("userOrder", request.getParameter("userOrder"));
            }
            String userOrder = (String) session.getAttribute("userOrder");

            if (request.getParameter("userPage") != null) {
                session.setAttribute("userPage", Integer.parseInt(request.getParameter("userPage")));
            }
            int userPage = (Integer) session.getAttribute("userPage");

            if (request.getParameter("usersActPage") != null) {
                session.setAttribute("usersActPage", Integer.parseInt(request.getParameter("usersActPage")));
            }
            int usersActPage = (Integer)(session.getAttribute( "usersActPage"));

            String usersActSortBy = assignStringSessionAttribute(request, session, "usersActSortBy");

            String usersActOrder = assignStringSessionAttribute(request, session, "usersActOrder");

            UsersActivityService usersActivityService = new UsersActivityService();

            List<User> users = userService.getSortedPortion(userSortBy, (userPage - 1) * pageSize, pageSize, userOrder);

            List<UsersActivity> usersActivities = usersActivityService.getPortionByUser(editUser, usersActSortBy, (usersActPage-1)*pageSize, pageSize, usersActOrder);

            int count = userService.getCount();
            int numberPages = count / pageSize;
            if (count % pageSize != 0) {
                numberPages++;
            }

            int usersActCount = usersActivityService.getCountByUser(editUser);
            int numberUsersActPages = usersActCount/pageSize;
            if (usersActCount % pageSize != 0){
                numberUsersActPages++;
            }
            request.setAttribute("usersActList", usersActivities);
            request.setAttribute("usersActPage", usersActPage);
            request.setAttribute("numUsersActPages", numberUsersActPages);
            request.setAttribute("userList", users);
            request.setAttribute("userPage", userPage);
            request.setAttribute("numPages", numberPages);
            request.setAttribute("userOrder", userOrder);
            RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
            rd.forward(request, response);
        } catch (ServiceException | NumberFormatException e) {
            logger.error(LogMsg.ERROR, e);
            try {
                response.sendRedirect("error.jsp");
            } catch (IOException ex) {
                logger.error(LogMsg.ERROR, ex);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //does nothing
    }

    private String assignStringSessionAttribute(HttpServletRequest request, HttpSession session, String name) {
        if(request.getParameter(name) != null) {
            session.setAttribute(name, request.getParameter(name));
        }
        return (String)session.getAttribute(name);
    }

}
