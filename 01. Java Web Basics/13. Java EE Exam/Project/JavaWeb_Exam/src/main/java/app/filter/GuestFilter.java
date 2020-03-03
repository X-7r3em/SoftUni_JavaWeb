package app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/faces/views/register.xhtml",
        "/faces/views/login.xhtml",
        "/faces/views/index.xhtml",
})
public class GuestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute("username") != null) {
            resp.sendRedirect("/faces/views/home.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}

