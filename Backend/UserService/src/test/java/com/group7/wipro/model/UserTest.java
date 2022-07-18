package com.group7.wipro.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserTest {
    private static User user;
    @BeforeAll
    public static void setUp() throws Exception {
        user = new User();
        user.setId(1);
        user.setFirstName("Arushi");
        user.setLastName("Kumar");
        user.setCity("Prayagraj");
        user.setMobile("1234567890");
        user.setEmail("arushi.ku22@gmail.com");
        user.setPassword("arushi123");
        user.setUserAddedDate(new Date());
    }
    @Test
    public void testUserIdShouldNotBeEmpty() {
        assertTrue(user.getId() == 1, "User Id should be 1");
    }
    @Test
    public void testUserFirstNameShouldNotBeEmpty() {
        assertTrue(user.getFirstName().equals("Arushi"), "User First Name should be Arushi");
    }
    @Test
    public void testUserLastNameShouldNotBeEmpty() {
        assertTrue(user.getLastName().equals("Kumar"), "User Last Name should be Kumar");
    }
    @Test
    public void testUserCityShouldNotBeEmpty() {
        assertTrue(user.getCity().equals("Prayagraj"), "User City should be Prayagraj");
    }
    @Test
    public void testUserMobileShouldNotBeEmpty() {
        assertTrue(user.getMobile().equals("1234567890"), "User Mobile should be 1234567890");
    }
    @Test
    public void testUserEmailShouldNotBeEmpty() {
        assertTrue(user.getEmail().equals("arushi.ku22@gmail.com"), "User Email should be arushi.ku22@gmail.com");
    }
    @Test
    public void testUserPasswordShouldNotBeEmpty() {
        assertTrue(user.getPassword().equals("arushi123"), "User Password should be arushi123");
    }
}
