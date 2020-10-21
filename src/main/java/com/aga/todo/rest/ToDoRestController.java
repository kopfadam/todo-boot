package com.aga.todo.rest;

import com.aga.todo.entity.ToDo;
import com.aga.todo.entity.User;
import com.aga.todo.service.ToDoService;
import com.aga.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ToDoRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ToDoService toDoService;

    @PostMapping("/todos")
    public ResponseEntity<ToDo> addToDo(@RequestBody ToDo toDo){
        User user = userService.getUserById(1);
        ToDo toDo1 = toDoService.addToDo(toDo, user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(toDo1.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/todos/{toDoId}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable int toDoId){

        try {
            return ResponseEntity.ok(toDoService.getToDoById(toDoId).get());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/todos")
    public ResponseEntity<List<ToDo>> getAllToDos(){

        return ResponseEntity.ok(toDoService.getAllToDos());
    }

    @GetMapping("/todos/user/{userId}")
    public ResponseEntity<List<ToDo>> getAllTodosByUser(@PathVariable int userId){

        User user = userService.getUserById(userId);

        return ResponseEntity.ok(toDoService.getAllToDosByUser(user));
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity deleteTodo(@PathVariable int todoId) {
        toDoService.delete(todoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/todos")
    public ResponseEntity<ToDo> updateTodo(@RequestBody ToDo todo) {

        return ResponseEntity.ok(toDoService.update(todo));
    }
}
