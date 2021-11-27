package ua.my.oblikchasu.servlet;

import org.apache.log4j.Logger;
import ua.my.oblikchasu.URL;
import ua.my.oblikchasu.util.LogMsg;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "1encodingFilter", urlPatterns = "/*")
public class FilterEncoding implements Filter {
    private static final Logger logger = Logger.getLogger(FilterEncoding.class);
    @Override
    public void init(FilterConfig config) throws ServletException {
        //empty
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String encoding = "utf-8";
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=UTF-8");
        if (request.getParameter("sessionLocale") != null) {
            request.getSession().setAttribute("language", request.getParameter("sessionLocale"));
            request.setAttribute("sessionLocale", null);
            logger.info(LogMsg.LANGUAGE_SET + request.getSession().getAttribute("language"));
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        //empty
    }
}
