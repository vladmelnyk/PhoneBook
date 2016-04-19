package com.vladik.dao;

import com.vladik.model.Contact;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ContactDao extends CrudRepository<Contact, Integer> {
}
