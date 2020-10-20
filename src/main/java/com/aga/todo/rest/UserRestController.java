package com.aga.todo.rest;

import com.aga.todo.entity.User;
import com.aga.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User registerUser(@RequestBody User user) {

        return userService.register(user);
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable int userId) {

        return userService.getUserById(userId);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {

        userService.delete(userId);

        return "Deleted successfully";
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {

        return userService.update(user);
    }

}
