package com.example.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(statefulServiceConfig.class);

    @Test
    @DisplayName("싱글톤이 stateful하게 설계되었는지 테스트한다.")
    void statefulServiceSingleton() {
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);

        int price1 = statefulService1.order("customer1", 10000);
        int price2 = statefulService1.order("customer2", 20000);

        System.out.println("price1 = " + price1);
        System.out.println("price2 = " + price2);
    }

    @Configuration
    static class statefulServiceConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
