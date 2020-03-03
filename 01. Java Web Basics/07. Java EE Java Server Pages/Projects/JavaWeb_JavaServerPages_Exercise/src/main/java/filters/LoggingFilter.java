package filters;

import services.DateService;
import utilities.LoggingUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoggingFilter implements Filter {
    private final DateService dateService;
    private final LoggingUtil loggingUtil;

    @Inject
    public LoggingFilter(DateService dateService, LoggingUtil loggingUtil) {
        this.dateService = dateService;
        this.loggingUtil = loggingUtil;
    }


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String time = dateService.getTimeNow();
        String url = req.getRequestURL().toString();
        String ip = req.getRemoteAddr();
        String method = req.getMethod();

        String log = String.format("[%s]: {%s} -> {%s} {%s}", time, url, ip, method);
        loggingUtil.log(log);
        filterChain.doFilter(req, resp);
    }
}
