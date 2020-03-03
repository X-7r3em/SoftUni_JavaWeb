package bg.softuni.filtersandauthentication.web.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//May not be a component only if it does need any other dependencies
public class AllInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("it works!");
        request.getSession().setAttribute("sin", "this is from session Interceptor!" );
        if (modelAndView == null) {
            return;
        }
        modelAndView.addObject("interceptor", "Hello world from Interceptor!");
    }
}


