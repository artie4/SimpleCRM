package app.config;

import app.entity.User;
import app.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@EnableWebSecurity
public class CustomSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomSecurityConfiguration(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/routeA/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/routeB/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/routeB/**").hasRole("ADMIN")
//                .and()
//                .requestCache().requestCache(new NullRequestCache())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .loginPage("/showLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(authenticationSuccessHandler())
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

    }

    // authenticationProvider bean definition
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            Logger logger = Logger.getLogger("loggerAuth");
            logger.info("\n\n In customAuthenticationSuccessHandler \n\n");
            String userName = authentication.getName();
            logger.info("userName= " + userName);
            User user = userService.findByUserName(userName);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user", user);
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
        };
    }
}
