package com.aga.todo.rest;

import com.aga.todo.entity.ToDo;
import com.aga.todo.entity.User;
import com.aga.todo.service.ToDoService;
import com.aga.todo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoRestController.class)
class ToDoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @MockBean
    private UserService userService;

    private List<ToDo> toDoList;
    private List<User> userList;

    @BeforeEach
    void setUp(){
        this.toDoList = new ArrayList<>();
        this.userList = new ArrayList<>();

        User user1 = new User("Alfonso","naber","alfonso@mail.com");
        user1.setCreated(LocalDateTime.now());
        user1.setModified(LocalDateTime.now());
        user1.setId(1);

        User user2 = new User("Alf","hello","alf@mail.com");
        user2.setCreated(LocalDateTime.now());
        user2.setModified(LocalDateTime.now());
        user2.setId(2);

        User user3 = new User("Alfred","no way","alfred@mail.com");
        user3.setCreated(LocalDateTime.now());
        user3.setModified(LocalDateTime.now());
        user3.setId(3);

        this.userList.add(user1);
        this.userList.add(user2);
        this.userList.add(user3);

        ToDo todo = new ToDo("Read a book", "Read a Hemingway book");
        todo.setId(1);
        todo.setCompleted(false);
        todo.setCreated(LocalDateTime.now());
        todo.setModified(LocalDateTime.now());
        todo.setUser(user1);

        ToDo todo2 = new ToDo("Clean house", "clean kitchen and living room");
        todo2.setId(2);
        todo2.setCompleted(false);
        todo2.setCreated(LocalDateTime.now());
        todo2.setModified(LocalDateTime.now());
        todo2.setUser(user1);

        ToDo todo3 = new ToDo("Buy ticket", "2 movie tickets for Wolverine");
        todo3.setId(3);
        todo3.setCompleted(true);
        todo3.setCreated(LocalDateTime.now());
        todo3.setModified(LocalDateTime.now());
        todo3.setUser(user1);

        ToDo todo4 = new ToDo("Go shopping", "buy milk, bread");
        todo4.setId(4);
        todo4.setCompleted(false);
        todo4.setCreated(LocalDateTime.now());
        todo4.setModified(LocalDateTime.now());
        todo4.setUser(user2);

        this.toDoList.add(todo);
        this.toDoList.add(todo2);
        this.toDoList.add(todo3);
        this.toDoList.add(todo4);
    }

    @Test
    void addToDo() {
    }

    @Test
    void getToDoById() throws Exception {
        final int id = 3;
        final ToDo toDo = toDoList.get(2);

        given(toDoService.getToDoById(id)).willReturn(Optional.of(toDo));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Buy ticket"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void getAllToDos() throws Exception{

    }

    @Test
    void getAllTodosByUser() {
    }

    @Test
    void deleteTodo() {
    }

    @Test
    void updateTodo() {
    }
}