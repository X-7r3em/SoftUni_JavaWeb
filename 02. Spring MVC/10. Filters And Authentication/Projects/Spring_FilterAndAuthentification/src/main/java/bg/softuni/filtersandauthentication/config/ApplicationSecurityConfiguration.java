package bg.softuni.filtersandauthentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/js/**", "/css/**").permitAll()
                    .antMatchers("/restrictedConfig").hasAuthority("ADMIN")
                    .antMatchers("/", "/register").anonymous()
                        .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login").permitAll()
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)
                .and()
                    .logout()
                        .logoutSuccessUrl("/login?logout").permitAll()
                .and()
                    .exceptionHandling()
                        .accessDeniedPage("/unauthorized");
    }
}
