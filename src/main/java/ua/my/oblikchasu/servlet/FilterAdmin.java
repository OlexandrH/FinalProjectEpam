package ua.my.oblikchasu.servlet;

import ua.my.oblikchasu.URL;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.db.entity.UserRole;

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
        "/user-edit",
        "/user-list"
})
public class FilterAdmin implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getSession().getAttribute("user") != null) {
            User user = (User)request.getSession().getAttribute("user");
            if(user.getRole() == UserRole.ADMIN) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            request.getRequestDispatcher("signin.jsp").forward(servletRequest, servletResponse);

        }
    }

    @Override
    public void destroy() {

    }
}
