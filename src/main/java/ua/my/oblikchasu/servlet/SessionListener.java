package ua.my.oblikchasu.servlet;
import org.apache.log4j.Logger;
import ua.my.oblikchasu.db.entity.User;
import ua.my.oblikchasu.util.LogMsg;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = Logger.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute("language", "en");
        session.setAttribute("usersActSortBy", "id");
        session.setAttribute("usersActPage", 1);
        session.setAttribute("usersActOrder", "desc");
        session.setAttribute("userSortBy", "id");
        session.setAttribute("userOrder", "asc");
        session.setAttribute("userPage", 1);
        session.setAttribute("activitySortBy", "id");
        session.setAttribute("activityOrder", "asc");
        session.setAttribute("activityPage", 1);
        session.setAttribute("categorySortBy", "name");
        session.setAttribute("categoryOrder", "asc");
        session.setAttribute("categoryPage", 1);

        logger.info(LogMsg.SESSION_CREATED + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        String sessionId = session.getId();
        String userLogin = ((User)session.getAttribute("user")).getLogin();
        logger.info(LogMsg.SESSION_DESTROYED + sessionId + LogMsg.SPACE + userLogin);
    }
}