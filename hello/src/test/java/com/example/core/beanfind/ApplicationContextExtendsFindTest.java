package com.example.core.beanfind;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.discount.FixedRatePolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ExtendsTestConfig.class);

    @DisplayName("부모타입 호출시 자식 클래스 빈까지 불려진다")
    @Test
    void findExtendsBeanTest1() {
        assertThatCode(() -> ac.getBean(DiscountPolicy.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @DisplayName("부모타입으로 조회시 빈 이름을 지정하면 정상수행 된다.")
    @Test
    void findExtendsBeanTest2() {
        DiscountPolicy bean = ac.getBean("discountPolicy1", DiscountPolicy.class);
        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);
    }

    @Configuration
    static class ExtendsTestConfig {
        @Bean
        public DiscountPolicy discountPolicy1() {
            return new FixDiscountPolicy();
        }

        @Bean
        public DiscountPolicy discountPolicy2() {
            return new FixedRatePolicy();
        }
    }
}
