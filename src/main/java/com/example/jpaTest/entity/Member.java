package com.example.jpaTest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
public class Member {
    @Id
    private String memberId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)  //EAGER 는 즉시 로딩,LAZY 는 지연 로딩
    @JoinColumn(name = "teamId")
    private Team team;
//포링키 대신 정보를 다 가지고 있다, 외래키는 항상 ManyToOne
//    단방향 매핑, 데이터가 많은 곳에서 적은 쪽으로



//1쪽에 붙은 건 lazy 가 기본
//    다쪽이면 EAGER 가 기본
}
