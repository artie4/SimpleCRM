package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    public String showLoginPage() {

        return "fancy-login";

    }

    // add request mapping for /access-denied
    // access-denied

    @GetMapping("/access-denied")
    public String s() {

        return "access-denied";

    }

}
