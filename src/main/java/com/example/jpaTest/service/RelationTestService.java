package com.example.jpaTest.service;

import com.example.jpaTest.entity.Child;
import com.example.jpaTest.entity.Member;
import com.example.jpaTest.entity.Parent;
import com.example.jpaTest.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RelationTestService {
    @Autowired
    EntityManager em;

    public void insertMemberAndTeam(){
//        새로운 멤버 생성 후 저장
        Member member = new Member();
        member.setMemberId("장원영");
        member.setName("원영");
//        member.setTeam()
        em.persist(member);

//        팀을 생성
        Team team = new Team();
        team.setTeamId("ive");
        team.setTeamName("아이브");
        em.persist(team);

    }

    public void insertMemberAndTeamRelation(){
        Team team = new Team();
        team.setTeamId("ive");
        team.setTeamName("아이브");
        em.persist(team);

        Member member = new Member();
        member.setMemberId("장원영");
        member.setName("원영");
        member.setTeam(team);
        em.persist(member);
    }

    public void insertBothDirectionRelation(){
//        뉴진스 팀을 생성하고 저장
        Team team = new Team();
        team.setTeamId("newJeans");
        team.setTeamName("뉴진스");
        em.persist(team);

//        뉴진스 멤버 --- 민지추가
        Member m1 = new Member();
        m1.setMemberId("MinJi");
        m1.setName("민지");
        m1.setTeam(team);
        em.persist(m1);

        //        뉴진스 멤버 --- 혜인추가
        Member m2 = new Member();
        m2.setMemberId("HyeIn");
        m2.setName("혜인");
        m2.setTeam(team);
        em.persist(m2);
    }


//    양방향 설정 중에 연관관계 주인이 아닌곳에 입력하는 경우
    public void insertBothMemberAndTeam(){
//        회원1 저장
        Member m1 = new Member();
        m1.setMemberId("강호동");
        m1.setName("포동포동");
        em.persist(m1);

//        회원2 저장
        Member m2 = new Member();
        m2.setMemberId("이만한기");
        m2.setName("만세");
        em.persist(m2);
//        씨름팀 저장
        Team team = new Team();
        team.setTeamId("씨름팀");
        team.setTeamName("시름시름");
        team.getMemberList().add(m1);
        team.getMemberList().add(m2);
        em.persist(team);
    }

    public void saveNoCascade(){
//        1번 부모저장
        Parent parent = new Parent();
        em.persist(parent);

//        1번 자식 저장
        Child child1 = new Child();
        child1.setParent(parent);
        parent.getChildList().add(child1);

//        2번 자식 저장
        Child child2 = new Child();
        child2.setParent(parent);
        parent.getChildList().add(child2);
        em.persist(child2);
    }

    public void  saveCascade(){
        Child child1 = new Child();
        Child child2 = new Child();


        Parent parent = new Parent();
        child1.setParent(parent);
        child2.setParent(parent);

        parent.getChildList().add(child1);
        parent.getChildList().add(child2);
//        부모만 저장 -- 자식들이 따라서 저장이 된다
        em.persist(parent);
    }

    public void cascadeDelete(){
        Parent parent = em.find(Parent.class,1L);
        em.remove(parent);
    }
}
