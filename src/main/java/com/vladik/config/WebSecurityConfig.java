package com.vladik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserDetailsServiceImpl userDetailsService;
    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/main", "/main/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/main/login")
                .defaultSuccessUrl("/main/contacts", true)
                .permitAll();
        http
                .logout()
                .permitAll()
                // указываем URL логаута
                .logoutUrl("/main/logout")
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/main/login?logout")
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true);
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
