package com.vladik.dao;

import com.vladik.model.Contact;
import com.vladik.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactDao {
    List<Contact> findByUser(User user);

    List<Contact> findByUserAndFirstNameContains(User user, String expression);


    List<Contact> searchByUserAndMobileNumber( String usersId, String mobileNumberExp);

    List<Contact> searchByUserAndMobileNumberAndFirstName(String usersId,  String mobileNumberExp,   String firstNameExp);

    <S extends Contact> S save(S s);
    void delete(Integer id);
    Contact findOne(Integer integer);
}
