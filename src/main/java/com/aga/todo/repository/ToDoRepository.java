package com.aga.todo.repository;

import com.aga.todo.entity.ToDo;
import com.aga.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    List<ToDo> findByUser(User user);
}
