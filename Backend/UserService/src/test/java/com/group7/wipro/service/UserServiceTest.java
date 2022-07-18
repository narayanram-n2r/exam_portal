package com.group7.wipro.service;

import com.group7.wipro.dao.UserDAO;
import com.group7.wipro.exception.UserAlreadyExistsException;
import com.group7.wipro.exception.UserNotFoundException;
import com.group7.wipro.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserDAO userDAO;
    User user;
    List<User> userList;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userList = new ArrayList<User>();
        user = new User("Arushi", "Kumar", "arushi@gmail.com", "arushi123", "1234567890", "Prayagraj",new Date());
        User user1 = new User("Aditya", "Kesharwani", "aditya@gmail.com", "aditya123", "987654321", "prayagraj", new Date());
        User user2 = new User("Manikya", "Shulka", "manikya@gmail.com", "manikya@123", "7741852963", "prayagraj", new Date());
        userList.add(user1);
        userList.add(user2);
//        userList.add(user);
    }

    @AfterEach
    public void tearDown() throws Exception {
        user = null;
        userList = null;
    }

    @Test
    @Rollback(true)
    public void testAddUserSuccess() throws UserAlreadyExistsException{
//        when(userDAO.ins).thenReturn(null);
        when(userDAO.save(any())).thenReturn(user);
        assertEquals(user, userService.addUser(user));
//        verify(userDAO, times(1)).getOne(any());
        verify(userDAO, times(1)).save(any());
    }

//    @Test
//    @Rollback(true)
//    public void testAddUserFailure() throws UserAlreadyExistsException {
////        when(userDAO.save(user)).thenReturn(user);
//////        when(userDAO.getOne(any())).thenReturn(user);
////        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
//////        verify(userDAO, times(1)).getOne(any());
////        verify(userDAO, times(0)).save(user);
////        when(userDAO.getOne(any())).thenReturn(user);
//        when(userService.addUser(user)).thenReturn(user);
//
//        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
//
////        verify(userDAO, times(1)).getOne(any());
////        verify(userDAO, times(0)).save(any());
//    }

    @Test
    @Rollback(true)
    public void testValidateUserFailure() throws UserNotFoundException {
        when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
        assertThrows(UserNotFoundException.class, () -> userService.validate(user.getEmail(), user.getPassword()));
//        verify(userDAO, times(1)).findById(any());
    }

//    @Test
//    @Rollback(true)
//    public void testValidateUserSuccess() throws UserNotFoundException {
////        when(userDAO.save(user)).thenReturn(user);
////        when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
////        assertEquals(user, userService.validate(user.getEmail(), user.getPassword()));
////        verify(userDAO, times(1)).findByEmail(user.getEmail());
//        when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
//        when(userDAO.save(user)).thenReturn(user);
//
//        assertEquals(user, userService.validate(user.getEmail(), user.getPassword()));
//
//        verify(userDAO, times(1)).findByEmail(user.getEmail());
//
//    }

    @Test
    @Rollback(true)
    public void testGetAllUserSuccess() {
        when(userDAO.findAll()).thenReturn(userList);
        assertEquals(userList, userService.getAllUsers());
        verify(userDAO, times(1)).findAll();
    }

    @Test
    @Rollback(true)
    public void testUpdateUserFailure() throws UserNotFoundException {
        when(userDAO.saveAndFlush(any())).thenReturn(user);
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(user, user.getEmail()));
        verify(userDAO, times(0)).saveAndFlush(any());
    }

    @Test
    @Rollback(true)
    public void testUpdateUserSuccess() throws UserNotFoundException {
        when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
        when(userDAO.save(user)).thenReturn(user);
        user.setCity("Allahabad");
        userList.add(user);
        User fetchedUser = userService.updateUser(user, user.getEmail());
        assertEquals(user, fetchedUser);

    }
}