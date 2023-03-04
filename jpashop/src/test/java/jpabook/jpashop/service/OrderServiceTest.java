package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook();

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order getOrder = orderRepository.findOne(orderId);
        //then

        assertEquals(OrderStatus.ORDER, getOrder.getStatus()); //주문 상태 테스트
        assertEquals(1, getOrder.getOrderItems().size()); //주문상품 종류 수
        assertEquals(20000, getOrder.getTotalPrice());  //가격 측정
        assertEquals(8, book.getStockQuantity());  //남은 재고 확인

    }

    @Test
    void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook();
        //when

        //then
        assertThatCode(() -> orderService.order(member.getId(), book.getId(), 40))
                .isInstanceOf(NotEnoughStockException.class);

    }

    @Test
    void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook();

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 1);
        orderService.cancelOrder(orderId);
        Order cancleOrder = orderRepository.findOne(orderId);

        //then
        assertEquals(cancleOrder.getStatus(), OrderStatus.CANCEL);
        assertEquals(10, book.getStockQuantity());

    }


    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;

    }

    private Book createBook() {
        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }
}