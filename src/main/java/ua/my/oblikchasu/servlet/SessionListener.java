package ua.my.oblikchasu.servlet;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute("language", "en");
        session.setAttribute("usersActSortBy", "id");
        session.setAttribute("usersActPage", 0);
        session.setAttribute("usersActOrder", "asc");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }
}