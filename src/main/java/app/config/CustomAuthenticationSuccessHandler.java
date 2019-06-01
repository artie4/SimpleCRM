package app.config;

import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.*;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Logger logger = Logger.getLogger("loggerAuth");

        logger.info("\n\n In customAuthenticationSuccessHandler \n\n");

        String userName = authentication.getName();

        logger.info("userName= " + userName);

        User user = userService.findByUserName(userName);

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", user);

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");

    }
}
