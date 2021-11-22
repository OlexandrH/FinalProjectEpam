package ua.my.oblikchasu;

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

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String text = request.getParameter("test");
        System.out.println(text);


    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String text = request.getParameter("test");
        System.out.println(text);

    }

}
