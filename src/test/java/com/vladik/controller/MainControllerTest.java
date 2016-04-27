package com.vladik.controller;

import com.vladik.Application;
import com.vladik.config.MvcConfig;
import com.vladik.config.WebSecurityConfig;
import com.vladik.dao.UserDao;
import com.vladik.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, MvcConfig.class, WebSecurityConfig.class, TestContext.class})
@WebAppConfiguration

public class MainControllerTest {

    @Mock
    private UserDao userDao;
    @InjectMocks
    private MainController mainController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).setCustomArgumentResolvers(new AuthenticationPrincipalArgumentResolver()).build();
    }

    @Test
    public void testSignUpGet() throws Exception {
        mockMvc.perform(get("/main/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("signuppage"))
                .andExpect(forwardedUrl("signuppage"));
    }

    @Test
    public void testSignUpPost() throws Exception {
        User user1 = new User();
        user1.setId(1);
        user1.setLogin("testlogin");
        user1.setPassword("password");
        user1.setFirstName("testname");
        user1.setMiddleName("testMiddleName");
        user1.setLastName("testlastname");
        mockMvc.perform(post("/main/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("login", user1.getLogin())
                .param("password", user1.getPassword())
                .param("firstName", user1.getFirstName())
                .param("middleName", user1.getMiddleName())
                .param("lastName", user1.getLastName())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("signedpage"))
                .andExpect(forwardedUrl("signedpage"))
                .andExpect(model().attribute("userAttribute", hasProperty("id", is(0))))
                .andExpect(model().attribute("userAttribute", hasProperty("login", is(user1.getLogin()))))
                .andExpect(model().attribute("userAttribute", hasProperty("password", is(user1.getPassword()))));
    }
}
