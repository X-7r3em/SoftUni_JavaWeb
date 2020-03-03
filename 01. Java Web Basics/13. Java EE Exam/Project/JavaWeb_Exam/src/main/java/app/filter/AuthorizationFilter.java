package app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/faces/views/home.xhtml",
        "/faces/views/hero-create.xhtml",
        "/faces/views/hero-details.xhtml",
        "/faces/views/hero-delete.xhtml",
})
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute("username") == null) {
            resp.sendRedirect("/faces/views/index.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
