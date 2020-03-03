package bg.softuni.filtersandauthentication.config;

import bg.softuni.filtersandauthentication.web.filter.AllInterceptor;
import bg.softuni.filtersandauthentication.web.filter.HomeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ServletInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private HomeInterceptor homeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllInterceptor());
        registry.addInterceptor(homeInterceptor)
                .addPathPatterns("/home");
    }
}
