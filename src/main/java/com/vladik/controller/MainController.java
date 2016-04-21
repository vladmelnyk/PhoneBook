package com.vladik.controller;

import com.vladik.dao.ContactDao;
import com.vladik.dao.UserDao;
import com.vladik.model.Contact;
import com.vladik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    public UserDao userDao;
    @Autowired
    public ContactDao contactDao;

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
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

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String getContacts(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Contact> contacts = userDao.findByLogin(user.getUsername()).getContacts();
        model.addAttribute("contacts", contacts);
        return "contactspage";
    }

    @RequestMapping(value = "/contacts/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        model.addAttribute("contactAttribute", new Contact());
        return "addpage";
    }

    @RequestMapping(value = "/contacts/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("contactAttribute") Contact contact) {
        //perhaps put it somewhere else
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contact.setUser(userDao.findByLogin(user.getUsername()));
        contactDao.save(contact);
        return "addedpage";
    }

    @RequestMapping(value = "/contacts/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) Integer id,
                         Model model) {
        contactDao.delete(id);
        model.addAttribute("id", id);
        return "deletedpage";
    }

    @RequestMapping(value = "/contacts/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id", required = true) Integer id,
                          Model model) {
        model.addAttribute("contactAttribute", contactDao.findOne(id));
        return "editpage";
    }

    @RequestMapping(value = "/contacts/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("contactAttribute") Contact contact,
                           @RequestParam(value = "id", required = true) Integer id,
                           Model model) {
        contact.setId(id);
        contact.setUser(userDao.findOne(3));
        // Delegate to PersonService for editing
        contactDao.save(contact);

        // Add id reference to Model
        model.addAttribute("id", id);

        // This will resolve to /WEB-INF/jsp/editedpage.jsp
        return "editedpage";
    }
    @RequestMapping(value = "/main/logout", method = RequestMethod.POST)
    public String logout()  {
        return "mainpage";
    }


}
