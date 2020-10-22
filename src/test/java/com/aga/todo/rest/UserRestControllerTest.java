package com.aga.todo.rest;

import com.aga.todo.entity.User;
import com.aga.todo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRestControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private List<User> userList;

    @Test
    void registerUser() {
        ResponseEntity<User> user = testRestTemplate.postForEntity("http://localhost:8080/api/users", new User("Ryan", "pass", "ryan@mail.com"), User.class);

        assertEquals(user.getStatusCode(), HttpStatus.CREATED);
        assertEquals(user.getBody().getEmail(),"ryan@mail.com");
    }

    @Test
    void getUserById() {
        final int id = 1;

        ResponseEntity<User> user = testRestTemplate.getForEntity("http://localhost:8080/api/users/" + id, User.class);

        assertEquals(user.getStatusCode(), HttpStatus.OK);

        // we populate h2 database in advance, check data.sql file for other names
        assertEquals(user.getBody().getEmail(),"mark@mail.com");
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}