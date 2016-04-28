package com.vladik.dao.mysql;

import com.vladik.dao.UserDao;
import com.vladik.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserDaoMySql extends JpaRepository<User,Integer>,UserDao {
    User findByLogin(String login);
}
