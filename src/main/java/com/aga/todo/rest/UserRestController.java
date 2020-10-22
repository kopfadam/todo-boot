package com.aga.todo.rest;

import com.aga.todo.entity.User;
import com.aga.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> registerUser(@RequestBody User user) {

        User registeredUser = userService.register(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(registeredUser.getId()).toUri();


        return ResponseEntity.created(location).body(new User(registeredUser.getEmail()));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {

        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId) {

        userService.delete(userId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.update(user));
    }

}
