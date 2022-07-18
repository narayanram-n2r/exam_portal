package com.group7.wipro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.group7.wipro.dao.UserDAO;
import com.group7.wipro.exception.UserAlreadyExistsException;
import com.group7.wipro.exception.UserNotFoundException;
import com.group7.wipro.model.User;
import com.group7.wipro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private MockMvc mockMvc;

    private User user;

    @Mock
    private UserDAO userDAO;
    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    List<User> userList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userList = new ArrayList<User>();
        user = new User("Arushi", "Kumar", "arushi@gmail.com", "arushi123", "1234567890", "Prayagraj",new Date());
        User user1 = new User("Aditya", "Kesharwani", "aditya@gmail.com", "aditya123", "987654321", "prayagraj", new Date());
        User user2 = new User("Manikya", "Shulka", "manikya@gmail.com", "manikya@123", "7741852963", "prayagraj", new Date());
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void addUserSuccess() throws Exception {
        when(userService.addUser(any())).thenReturn(user);
        System.out.println("test.............");
        mockMvc.perform(post("/signup").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addUserFailure() throws Exception {
        when(userService.addUser(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateUserSuccess() throws Exception {
        when(userService.updateUser(user, user.getEmail())).thenReturn(user);
        mockMvc.perform(post("/update/arushi@gmail.com").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateNewsFailure() throws Exception {
        when(userService.updateUser(any(),any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(post("/update/arushik@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllUsersSuccess() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void loginUserSuccess() throws Exception {
        when(userService.validate(any(),any())).thenReturn(user);
        System.out.println("test.............");
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void loginUserFailure() throws Exception {
        when(userService.validate(any(),any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objmapper = new ObjectMapper();
            objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objmapper.registerModule(new JavaTimeModule());
            return objmapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}