package com.controller;

import com.entity.User;
import com.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    public UserService userService;


    @InjectMocks
    public UserController userController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void Successlogin() throws Exception {
        User u=new User();
        List<User> user1=new ArrayList<User>();
        user1.add(u);
        Mockito.when(
                userService.login("hknitw@gmail.com","1q2w3e4r")).thenReturn(user1);
        String json = "{\n" +
                "  \"username\": \"hknitw@gmail.com\",\n" +
                "  \"password\": \"1q2w3e4r\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.content().string("200"));
    }

    @Test
    public void Failedlogin() throws Exception {
        User u=new User();
        List<User> user1=new ArrayList<User>();
        user1.add(u);
        Mockito.when(
                userService.login("Harikrishna","t7t7t7t7")).thenReturn(user1);
        String json = "{\n" +
                "  \"username\": \"Harikrishna\",\n" +
                "  \"password\": \"t7t7t7t7\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.content().string("404"));
    }

}