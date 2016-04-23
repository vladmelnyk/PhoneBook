package com.vladik;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

import static org.assertj.core.api.Assertions.assertThat;

//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testHome() throws Exception {

//        this.mvc.perform(get("/main")).andExpect(status().isOk())
//                .andExpect(content().string("Bath"));
    }

    @Test
    public void testJmx() throws Exception {
        assertThat(ManagementFactory.getPlatformMBeanServer()
                .queryMBeans(new ObjectName("jpa.sample:type=ConnectionPool,*"), null))
                .hasSize(1);
    }

}