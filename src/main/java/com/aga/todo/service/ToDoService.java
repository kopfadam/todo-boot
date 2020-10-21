package com.aga.todo.service;

import com.aga.todo.entity.ToDo;
import com.aga.todo.entity.User;
import com.aga.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo addToDo(ToDo toDo, User user){

        toDo.setUser(user);
        toDo = toDoRepository.save(toDo);

        return toDo;
    }

    public Optional<ToDo> getToDoById(int toDoId){

        Optional<ToDo> toDo = toDoRepository.findById(toDoId);

        if (!toDo.isPresent())
            throw new RuntimeException("Nothing found");

        return toDo;
    }

    public List<ToDo> getAllToDos(){
        return toDoRepository.findAll();
    }

    public List<ToDo> getAllToDosByUser(User user){
        return toDoRepository.findByUser(user);
    }

    public void delete(int todoId) {
        toDoRepository.deleteById(todoId);
    }

    public ToDo update(ToDo toDo){
        return toDoRepository.save(toDo);
    }
}
