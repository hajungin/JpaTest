package com.example.jpaTest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Parent {
    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();
}
