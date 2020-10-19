package com.aga.todo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ToDo {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String title;

    @NotBlank
    private String description;

    @Column(updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed = false;

    public ToDo(String description){
        this.description = description;
    }

    public ToDo(String title, String description){
        this.title = title;
        this.description = description;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

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

    @Override
    public String toString() {
        return "ToDo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", completed=" + completed +
                '}';
    }
}
