package com.example.jpaTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    @Id
    private String teamId;
//    primaryKey

    private String teamName;

    @OneToMany(mappedBy = "team" , fetch = FetchType.LAZY) //Member 에 갖고 있는 team 과 연결
    private List<Member> memberList = new ArrayList<>();
//    초기화를 시켜줘야 빈 깡통을 가지고 있는다


}
