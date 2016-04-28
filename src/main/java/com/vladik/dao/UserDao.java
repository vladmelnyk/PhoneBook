package com.vladik.dao;


import com.vladik.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  {
    User findByLogin(String login);
    <S extends User> S save(S s);
}
