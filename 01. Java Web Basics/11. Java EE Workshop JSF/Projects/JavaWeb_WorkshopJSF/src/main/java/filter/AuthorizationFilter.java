package filter;

import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "/faces/view/home.xhtml",
        "/faces/view/job-add.xhtml",
        "/faces/view/job-details.xhtml",
        "/faces/view/job-delete.xhtml",
        "/faces/view/logout.xhtml",
})
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getSession().getAttribute("username") == null) {
            System.out.println("it works to here");
            resp.sendRedirect("/faces/view/login.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
