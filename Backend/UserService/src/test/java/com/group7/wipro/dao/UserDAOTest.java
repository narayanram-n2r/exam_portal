package com.group7.wipro.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static sun.nio.cs.Surrogate.is;
import com.group7.wipro.model.User;
//import org.mockito.MockitoAnnotations;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDAOTest {

    @Autowired
    private UserDAO userDao;

    private User user;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setFirstName("Arushi");
        user.setLastName("Kumar");
        user.setCity("Prayagraj");
        user.setMobile("1234567890");
        user.setEmail("arushi.ku22@gmail.com");
        user.setPassword("arushi123");
        user.setUserAddedDate(new Date());
    }

    @AfterEach
    public void tearDown() throws Exception {
        userDao.deleteAll();
    }

    @Test
    public void testUserSavingSuccess() {
        userDao.save(user);
        User fetchUser = userDao.findByEmail("arushi.ku22@gmail.com");
        assertThat(user.getEmail(), is(fetchUser.getEmail()));
    }

    @Test
    public void testLoginUserSuccess() {
        userDao.save(user);
        User fetchUser = userDao.findByEmailAndPassword(user.getEmail(),user.getPassword());
        assertThat(user.getEmail(), is(fetchUser.getEmail()));
    }
}