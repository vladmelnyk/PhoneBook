package com.vladik.config;

import com.vladik.dao.UserDao;
import com.vladik.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        // с помощью нашего сервиса UserService получаем User
        User user = userDao.findByLogin(login);
        // указываем роли для этого пользователя
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority("USER"));

        // на основании полученныйх даных формируем объект UserDetails
        // который позволит проверить введеный пользователем логин и пароль
        // и уже потом аутентифицировать пользователя
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getLogin(),
                        user.getPassword(),
                        roles);

        return userDetails;
    }
}
