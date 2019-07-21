package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    public ModelAndView showLoginPage() {

        return new ModelAndView("fancy-login");

    }

    // add request mapping for /access-denied
    // access-denied

    @GetMapping("/access-denied")
    public String s() {

        return "/WEB-INF/view/access-denied.html";

    }

}
