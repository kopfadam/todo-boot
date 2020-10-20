package com.aga.todo.rest;

import com.aga.todo.entity.ToDo;
import com.aga.todo.entity.User;
import com.aga.todo.service.ToDoService;
import com.aga.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ToDoRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ToDoService toDoService;

    @PostMapping("/todos")
    public ToDo addToDo(@RequestBody ToDo toDo){
        User user = userService.getUserById(1);

        return toDoService.addToDo(toDo, user);
    }

    @GetMapping("/todos/{toDoId}")
    public ToDo getToDoById(@PathVariable int toDoId){

        return toDoService.getToDoById(toDoId);
    }

    @GetMapping("/todos")
    public List<ToDo> getAllToDos(){

        return toDoService.getAllToDos();
    }

    @GetMapping("/todos/user/{userId}")
    public List<ToDo> getAllTodosByUser(@PathVariable int userId){

        User user = userService.getUserById(userId);

        return toDoService.getAllToDosByUser(user);
    }

    @DeleteMapping("/todos/{todoId}")
    public String deleteTodo(@PathVariable int todoId) {
        toDoService.delete(todoId);

        return "Deleted successfully";
    }

    @PutMapping("/todos")
    public ToDo updateTodo(@RequestBody ToDo todo) {

        return toDoService.update(todo);
    }
}
