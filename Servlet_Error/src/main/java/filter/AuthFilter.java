package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/reg", "/aut", "/main_page"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Boolean isAuthenticated = false;
        Boolean isLoginPage = request.getRequestURI().equals("/aut");
        Boolean isRegPage = request.getRequestURI().equals("/reg");
        Boolean isProfilePage = request.getRequestURI().equals("/main_page");

        if (session != null) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");
            if (isAuthenticated == null) {
                isAuthenticated = false;
            }

        }

        if (isProfilePage && !isAuthenticated) {
            response.sendRedirect("/aut");
        } else if ((isAuthenticated && isRegPage) || (!isAuthenticated && !isLoginPage)) {
            filterChain.doFilter(request, response);
        } else if((isAuthenticated && !isLoginPage) || (!isAuthenticated && isLoginPage)) {
            filterChain.doFilter(request, response);
            System.out.println("Он не нашел сессию");
        } else if (isAuthenticated && isLoginPage) {
            System.out.println("Он нашел сессию");
            response.sendRedirect("/main_page");
        } else {
            response.sendRedirect("/aut");
        }
    }

    @Override
    public void destroy() {

    }
}














