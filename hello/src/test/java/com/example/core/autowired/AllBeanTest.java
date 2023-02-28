package com.example.core.autowired;

import com.example.core.AutoAppConfig;
import com.example.core.discount.DiscountPolicy;
import com.example.core.member.Grade;
import com.example.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean_fixedRatePolicy() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member1 = new Member(1L, "userA", Grade.VIP);

        int discount = discountService.discount(member1, 12000, "fixedRatePolicy");
        assertThat(discount).isEqualTo(1200);

    }

    @Test
    void findAllBean_fixDiscountPolicy() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member1 = new Member(1L, "userA", Grade.VIP);

        int discount = discountService.discount(member1, 12000, "fixDiscountPolicy");
        assertThat(discount).isEqualTo(1000);

    }


    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

        }


        public int discount(Member member, int price, String beanCode) {
            DiscountPolicy discountPolicy = policyMap.get(beanCode);
            return discountPolicy.discount(member, price);

        }
    }
}
