package com.vladik.controller;

import com.vladik.dao.ContactDao;
import com.vladik.dao.UserDao;
import com.vladik.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private UserDao userDao;
    @Autowired
    @Qualifier("contactDaoMySql")
    private ContactDao contactDao;

    @RequestMapping(method = RequestMethod.GET)
    public String getContacts(Model model, @AuthenticationPrincipal User user) {
        List<Contact> contacts = userDao.findByLogin(user.getUsername()).getContacts();
        model.addAttribute("contacts", contacts);
        return "contactspage";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getContactsByExpression(@RequestParam(value = "first_name_exp", required = false) String firstNameExp, @RequestParam(value = "mobile_number_exp", required = false) String mobileNumberExp, Model model, @AuthenticationPrincipal User user) {
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
    public String addContact(@ModelAttribute("contactAttribute") Contact contact, @AuthenticationPrincipal User user) {
        contact.setUser(userDao.findByLogin(user.getUsername()));
        contactDao.save(contact);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteContact(@RequestParam(value = "id") Integer id) {
        contactDao.delete(id);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/editview", method = RequestMethod.GET)
    public String getEditView(@RequestParam(value = "id") Integer id,
                              Model model) {
        model.addAttribute("contactAttribute", contactDao.findOne(id));
        return "editviewpage";
    }

    @RequestMapping(value = "/contacts/update", method = RequestMethod.POST)
    public String updateContact(@ModelAttribute("contactAttribute") Contact contact,
                                @RequestParam(value = "id") Integer id,
                                Model model, @AuthenticationPrincipal User user) {
        contact.setId(id);
        contact.setUser(userDao.findByLogin(user.getUsername()));
        contactDao.save(contact);
        model.addAttribute("id", id);
        return "redirect:/contacts";
    }

}
