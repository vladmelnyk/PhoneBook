package com.vladik.dao;


import com.vladik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends JpaRepository<User,Integer> {

    public User findByLogin(String login);
}
