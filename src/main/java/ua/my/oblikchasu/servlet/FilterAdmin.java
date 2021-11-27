package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.URL;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;
import ua.my.oblikchasu.db.exception.ErrorMsg;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "adminAccessFilter", urlPatterns = {
        "/admin.jsp",
        "/activity-add",
        "/activity-edit",
        "/activity-list",
        "/category-add",
        "/category-list",
        "/user-delete",
        "/user-list"
})
public class FilterAdmin implements Filter {
    private static final Logger logger = Logger.getLogger(FilterAdmin.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //empty
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            if (request.getSession().getAttribute("user") != null) {
                User user = (User) request.getSession().getAttribute("user");
                if (user.getRole() == UserRole.ADMIN) {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                request.getRequestDispatcher("signin.jsp").forward(servletRequest, servletResponse);
            }
        } catch (ServletException | IOException e) {
            logger.error(ErrorMsg.ERROR, e);
            try {
                ((HttpServletResponse)servletResponse).sendRedirect("error.jsp");
            } catch (IOException ioException) {
                logger.error(ErrorMsg.ERROR, ioException);
            }
        }
    }

    @Override
    public void destroy() {
        //empty
    }
}
