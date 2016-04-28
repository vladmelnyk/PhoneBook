package com.vladik.service;

import com.vladik.dao.UserDao;
import com.vladik.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "${implementation.user}")
    private UserDao userDao;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                roles);
    }
}
