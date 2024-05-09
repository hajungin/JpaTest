package com.example.jpaTest.service;

import com.example.jpaTest.entity.Member;
import com.example.jpaTest.entity.Parent;
import com.example.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class RelationTestServiceTest {
    @Autowired
    RelationTestService relationTestService;

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("멤버와 팀 추가 테스트")
    void insertMemberAndTeam() {
        relationTestService.insertMemberAndTeam();
    }

    @Test
    @DisplayName("ID:장원영의 팀이름 찾기")
    void searchTeam(){
        Member member = em.find(Member.class,"장원영");
        Team team = em.find(Team.class,member.getTeam().getTeamId());
        System.out.println("Team Name ========" + team.getTeamName());
    }

    @Test
    @DisplayName("단방향 연관관계-데이터 입력")
    void insertMemberAndTeamR(){
        relationTestService.insertMemberAndTeamRelation();
    }

    @Test
    @DisplayName("단방향 설정 후 장원영 팀 이름 찾기")
    public void memberFindTeamName(){
        Member member = em.find(Member.class,"장원영");
        System.out.println(member.getTeam().getTeamName());
    }

    @Test
    @DisplayName("양방향 연관 관계 - 입력 테스트")
    public void bothRelationInputTest(){
        relationTestService.insertBothDirectionRelation();
    }

    @Test
    @DisplayName("양방향 설정 후 멤버의 팀 이름 출력")
    void teamOfMemberPrint(){
        Member member = em.find(Member.class,"MinJi");
        System.out.println("Team name : " + member.getTeam().getTeamName());
    }

    @Test
    @Transactional
    @DisplayName("뉴진스 팀 멤버 출력하기")
    public void findTeamMembers(){
        Team newJeans = em.find(Team.class,"newJeans");
        List<Member> memberList = newJeans.getMemberList();
        System.out.println("Member Size = " + memberList.size());
        for (Member m : memberList){
            System.out.println(m.toString());
        }
    }

    @Test
    @DisplayName("연관관계 주인 아닌곳에 입력 테스트")
    public void bothRelationErrorTest(){
        relationTestService.insertBothMemberAndTeam();
    }

    @Test
    @DisplayName("No Cascade")
    void noCascade(){
        relationTestService.saveNoCascade();
    }

    @Test
    @DisplayName("Use Persist Cascade")
    void cascade(){
        relationTestService.saveCascade();
    }

    @Test
    @DisplayName("Cascade Delete")
    public void cascadeDelete(){
        relationTestService.cascadeDelete();
    }

}