package com.example.jpaTest.service;

import com.example.jpaTest.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContextService {
    @Autowired
    EntityManager em;

    public Member memberInsert(){
        Member member = new Member();
        member.setMemberId("안유진");
        member.setName("호동");
        em.persist(member);
        Member m = em.find(Member.class,"안유진");
        return m;
//        레포지스토리를 사용하지 않아도 엔티티매니저를 사용하여 캐시에 저장하는 느낌으로 사용한다
    }

    public void transactionTest(){
        Member m1 = new Member();
        m1.setMemberId("홍길동");
        m1.setName("심청이");

        Member m2 = new Member();
        m2.setMemberId("이순신");
        m2.setName("달려라 이순신");

        em.persist(m1);
        em.find(Member.class,"홍길동");
        System.out.println(m1.toString());


        em.persist(m2);
        em.find(Member.class,"이순신");
        System.out.println(m2.toString());

        em.flush();
//        중간중간 변경사항이 생기면 flush 를 사용 어차피 트랜잭션이 마무리 되면 저장되기 때문
//        flush 트랜잭션 종료, 디비에 저장, 커밋이 떨어지면 저장 이것을 쓰기 지연이라고 한다
    }

    public void dirtyChecking(){
//        영속 엔티티 조회
        Member member = em.find(Member.class,"안유진");
//        데이터 수정
//        변경감지
        member.setName("Yujin");
        System.out.println(member.toString());
    }

    public void deleteCheck(){
        Member member = em.find(Member.class,"안유진");
        em.remove(member);
    }
}
