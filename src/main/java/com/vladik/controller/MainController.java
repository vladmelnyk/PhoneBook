package com.vladik.controller;

import com.vladik.dao.UserDao;
import com.vladik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.GET)
    public String main() {
        return "mainpage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userAttribute", new User());
        return "loginpage";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignUp(Model model) {
        model.addAttribute("userAttribute", new User());
        return "signuppage";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("userAttribute") User user) {
        userDao.save(user);
        return "signedpage";
    }

    @RequestMapping(value = "/main/logout", method = RequestMethod.GET)
    public String logout() {
        return "mainpage";
    }


}
