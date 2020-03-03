package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/cars/all",
        "/cars/create",
        "/home"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Object username = req.getSession().getAttribute("user");
        HttpServletResponse resp = (HttpServletResponse) response;

        if (username == null) {
            resp.sendRedirect("/users/login");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
