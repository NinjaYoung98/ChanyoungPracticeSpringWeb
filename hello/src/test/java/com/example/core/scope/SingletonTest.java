package com.example.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    @Test
    void singletonBeanFind() {
        try (AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class)) {
            SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
            SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

            assertThat(singletonBean1).isSameAs(singletonBean2);
        }
    }

    @Scope("singleton")
    static class SingletonBean {
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
