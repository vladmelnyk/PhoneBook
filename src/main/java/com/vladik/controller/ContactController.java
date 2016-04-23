package com.vladik.controller;

import com.vladik.dao.ContactDao;
import com.vladik.dao.UserDao;
import com.vladik.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ContactDao contactDao;

    @RequestMapping(method = RequestMethod.GET)
    public String getContacts(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Contact> contacts = userDao.findByLogin(user.getUsername()).getContacts();
        model.addAttribute("contacts", contacts);
        return "contactspage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getContactsByExpression(@RequestParam(value = "first_name_exp", required = false) String firstNameExp, @RequestParam(value = "mobile_number_exp", required = false) String mobileNumberExp, Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Contact> contacts = contactDao.findByUserAndFirstNameContains(userDao.findByLogin(user.getUsername()), firstNameExp);

        if (!mobileNumberExp.isEmpty()) {
            contacts = contactDao.searchByUserAndMobileNumber(String.valueOf(userDao.findByLogin(user.getUsername()).getId()), mobileNumberExp);
        }
        if (!mobileNumberExp.isEmpty() && !firstNameExp.isEmpty()) {
            contacts = contactDao.searchByUserAndMobileNumberAndFirstName(String.valueOf(userDao.findByLogin(user.getUsername()).getId()), mobileNumberExp, firstNameExp);
        }
        model.addAttribute("contacts", contacts);
        return "contactspage";
    }

    @RequestMapping(value = "/createview", method = RequestMethod.GET)
    public String getCreateView(Model model) {
        model.addAttribute("contactAttribute", new Contact());
        return "createviewpage";
    }

    @RequestMapping(value = "/createview", method = RequestMethod.POST)
    public String addContacts(@ModelAttribute("contactAttribute") Contact contact) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contact.setUser(userDao.findByLogin(user.getUsername()));
        contactDao.save(contact);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteContacts(@RequestParam(value = "id", required = true) Integer id) {
        contactDao.delete(id);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/editview", method = RequestMethod.GET)
    public String getEditView(@RequestParam(value = "id", required = true) Integer id,
                              Model model) {
        model.addAttribute("contactAttribute", contactDao.findOne(id));
        return "editviewpage";
    }

    @RequestMapping(value = "/contacts/update", method = RequestMethod.POST)
    public String updateContacts(@ModelAttribute("contactAttribute") Contact contact,
                                 @RequestParam(value = "id", required = true) Integer id,
                                 Model model) {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        contact.setId(id);
        contact.setUser(userDao.findByLogin(user.getUsername()));
        contactDao.save(contact);
        model.addAttribute("id", id);
        return "redirect:/contacts";
    }

}
