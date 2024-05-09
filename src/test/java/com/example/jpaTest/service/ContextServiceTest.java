package com.example.jpaTest.service;

import com.example.jpaTest.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class ContextServiceTest {
    @Autowired
    ContextService contextService;

    @Test
    @DisplayName("1차 캐시 테스트")
    void memberInsert() {
        Member m = contextService.memberInsert();
        System.out.println("==================" + m.toString());
    }

    @Test
    @DisplayName("트랜잭션 커밋 테스트")
     void tractionTest(){
        contextService.transactionTest();
    }

    @Test
    @DisplayName("Dirty Checking Test")
     void DirtyCheckingTest(){
        contextService.dirtyChecking();
    }

    @Test
    @DisplayName("삭제")
    void DeleteTest(){
        contextService.deleteCheck();
    }
}