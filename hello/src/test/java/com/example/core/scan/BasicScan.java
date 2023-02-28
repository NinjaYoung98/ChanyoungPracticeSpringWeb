package com.example.core.scan;

import com.example.core.AutoAppConfig;
import com.example.core.discount.DiscountPolicy;
import com.example.core.member.MemberRepository;
import com.example.core.member.MemberService;
import com.example.core.member.MemoryMemberRepository;
import com.example.core.order.OrderService;
import com.example.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BasicScan {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberServiceImpl = ac.getBean("memberServiceImpl", MemberService.class);
        MemberRepository memoryMemberRepository = ac.getBean("memoryMemberRepository", MemberRepository.class);
        OrderService orderServiceImpl = ac.getBean("orderServiceImpl", OrderService.class);


    }
}
