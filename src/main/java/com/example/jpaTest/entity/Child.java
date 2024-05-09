package com.example.jpaTest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Child {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;




}
