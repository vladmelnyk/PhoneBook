package com.vladik.dao;


import com.vladik.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User,Integer> {

}
