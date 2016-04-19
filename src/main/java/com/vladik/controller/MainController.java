package com.vladik.controller;

import com.vladik.dao.ContactDao;
import com.vladik.dao.UserDao;
import com.vladik.model.Contact;
import com.vladik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    @Autowired
    public UserDao userDao;
    @Autowired
    public ContactDao contactDao;
    @RequestMapping("/hello")
    @ResponseBody
    String showSomething (){
//         return userDao.findAll().toString();
        return userDao.findAll().toString();
    }
    @RequestMapping("/addUser")
    void add(){
        User user = new User();
        user.setFirstName("Vlad");
        user.setLastName("Melnyk");
        user.setLogin("login");
        user.setMiddleName("Vitalievich");
        user.setPassword("pass");
        userDao.save(user);
    }
    @RequestMapping("/addContact")
    void addContact(){
        Contact contact = new Contact();
        contact.setFirstName("some name");
        contact.setMiddleName("Vitalievich");
        contact.setLastName("Verino");
        contact.setAddress("some address");
        contact.setMobileNumber(3443454);
        User user = userDao.findOne(1);
        contact.setUser(user);
        contactDao.save(contact);

    }

}
