package com.example.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanTest() {
        try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class)) {
            PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
            PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

            assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
            //prototypeBean1.destroy();

        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("Singleton.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Singleton.destroy");
        }
    }
}
