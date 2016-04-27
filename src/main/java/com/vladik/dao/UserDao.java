package com.vladik.dao;


import com.vladik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByLogin(String login);
}
