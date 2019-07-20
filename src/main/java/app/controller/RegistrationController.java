package app.controller;

import app.entity.User;
import app.model.CrmUser;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public ModelAndView showMyLoginPage(Model theModel) {

        ModelAndView modelAndView = new ModelAndView("registrationForm");
        modelAndView.addObject("crmUser", new CrmUser());
        modelAndView.setViewName("registration-form");
        return modelAndView;

    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
            BindingResult theBindingResult,
            Model theModel) {

        String userName = theCrmUser.getUserName();

        // validation form
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }

        // check if user exists
        User existing = null;
        try {
            existing = userService.findByUserName(userName);
        } catch (NoResultException nre) {

        }
        if (existing!=null) {
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            return "registration-form";
        }

        // store the new account
        userService.save(theCrmUser);

        return "registration-confirmation";
    }


}
