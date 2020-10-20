package com.aga.todo.service;

import com.aga.todo.entity.ToDo;
import com.aga.todo.entity.User;
import com.aga.todo.repository.ToDoRepository;
import com.aga.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public User register(User user){

        return userRepository.save(user);
    }

    public User getUserById(int id){

        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new RuntimeException("Could not find such a user");

        return user.get();
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void delete(int userId) {
        List<ToDo> toDos = toDoRepository.findByUser(getUserById(userId));

        if (toDos.size() > 0)
            toDoRepository.deleteInBatch(toDos);

        entityManager.flush();
        entityManager.clear();

        userRepository.deleteById(userId);
    }

    public User update(User user) {

        return userRepository.save(user);
        
    }
}
