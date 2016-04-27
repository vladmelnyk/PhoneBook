package com.vladik.controller;

import com.vladik.Application;
import com.vladik.dao.ContactDaoMySql;
import com.vladik.dao.UserDao;
import com.vladik.model.Contact;
import com.vladik.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

@WebAppConfiguration

public class ContactControllerTest {

    @Mock
    private UserDao userDao;
    @Mock
    private ContactDaoMySql contactDaoMySql;
    @InjectMocks
    private ContactController contactController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).setCustomArgumentResolvers(new AuthenticationPrincipalArgumentResolver()).build();
    }

    @Test
    @WithMockUser
    public void testGetContacts() throws Exception {
        when(userDao.findByLogin(anyString())).thenReturn(new User());
        mockMvc.perform(get("/contacts")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("contactspage"))
                .andExpect(forwardedUrl("contactspage"));
        verify(userDao, times(1)).findByLogin(anyString());
    }

    @Test
    public void testGetCreateView() throws Exception {
        mockMvc.perform(get("/contacts/createview")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("createviewpage"))
                .andExpect(forwardedUrl("createviewpage"));
    }

    @Test
    @WithMockUser
    public void testAddContact() throws Exception {
        when(userDao.findByLogin(anyString())).thenReturn(new User());
        mockMvc.perform(post("/contacts/createview")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf())
        )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:/contacts"))
                .andExpect(redirectedUrl("/contacts"));
        verify(userDao, times(1)).findByLogin(anyString());
    }

    @Test
    public void testDeleteContact() throws Exception {

        mockMvc.perform(post("/contacts/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf())
                .param("id", String.valueOf(anyInt()))
        )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:/contacts"))
                .andExpect(redirectedUrl("/contacts"));
    }

    @Test
    public void testGetEditView() throws Exception {
        mockMvc.perform(get("/contacts/editview")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", String.valueOf(anyInt()))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("editviewpage"))
                .andExpect(forwardedUrl("editviewpage"));
    }

    @Test
    @WithMockUser
    public void testUpdateContact() throws Exception {
        mockMvc.perform(post("/contacts/createview")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf())
                .param("id", String.valueOf(anyInt()))
        )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:/contacts"))
                .andExpect(redirectedUrl("/contacts"));
    }

    @Test
    @WithMockUser
    public void testGetContactsByExpression() throws Exception {
        when(contactDaoMySql.searchByUserAndMobileNumberAndFirstName(anyString(), anyString(), anyString())).thenReturn(new ArrayList<Contact>());
        mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).with(csrf())
                .param("mobile_number_exp", anyString())
        )
                .andExpect(status().isOk())
                .andExpect(view().name("contactspage"))
                .andExpect(forwardedUrl("contactspage"));
    }


}
