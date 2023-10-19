package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/settings")
public class SettingsPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] coloooor = (request.getCookies());
        System.out.println(coloooor[0].getValue() + " " + coloooor[0].getName());
        System.out.println(coloooor[1].getValue() + " " + coloooor[1].getName());
        System.out.println("*****************************************");
        request.getRequestDispatcher("/settings.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String color = request.getParameter("color");
        Cookie colorCookie = new Cookie("color",color);
        response.addCookie(colorCookie);
        colorCookie.setMaxAge(3600 * 24);
        response.sendRedirect("/settings");

    }
}