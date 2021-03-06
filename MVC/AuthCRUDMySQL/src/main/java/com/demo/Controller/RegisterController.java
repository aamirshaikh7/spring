package com.demo.Controller;

import com.demo.Model.Login;
import com.demo.Model.User;
import com.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("register");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(user);

        return "redirect:login";
    }
}
