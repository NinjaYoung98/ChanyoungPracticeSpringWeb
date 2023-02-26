package com.example.core.scan;

import com.example.core.ConflictConfig;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConflictConfigTest {
    @Test
    void conflictConfigTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ConflictConfig.class);
        ac.getBean("memoryMemberRepository", MemberRepository.class);

    }

}
