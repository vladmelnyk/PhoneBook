package com.vladik.dao;

import com.vladik.model.Contact;
import com.vladik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ContactDao extends JpaRepository<Contact, Integer> {
    public List<Contact> findByUser(User user);

    public List<Contact> findByUserAndFirstNameContains(User user, String expression);

    @Query(value = "SELECT c.id, c.users_id, c.first_name, c.middle_name, c.last_name, c.mobile_number, c.phone_number, c.address, c.email FROM Contacts c WHERE c.users_id = :usersId AND c.mobile_number LIKE %:mobileNumberExp%", nativeQuery = true)
    public List<Contact> searchByUserAndMobileNumber(@Param("usersId") String usersId, @Param("mobileNumberExp") String mobileNumberExp);
}
