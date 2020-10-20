package com.aga.todo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    @Column(unique = true)
    private String email;

    @Column(updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;

    public User() {
    }

    public User(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @PrePersist
    void onCreate(){
        LocalDateTime date = LocalDateTime.now();
        this.created = date;
        this.modified = date;
    }

    @PreUpdate
    void onUpdate(){
        this.modified = LocalDateTime.now();
    }
}
