package com.vladik.dao;

import com.vladik.model.Contact;
import com.vladik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ContactDao extends JpaRepository<Contact, Integer> {
    public List<Contact> findByUser (User user);
}
