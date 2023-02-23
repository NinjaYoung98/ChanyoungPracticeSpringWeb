package com.example.core.order;

import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.member.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    private final int price = 10000;
    private final int fixDiscount = 1000;

    @Test
    void order() {
        //given
        Member member = new Member(1L, "ccy", Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderService.createOrder(1L, "맥북", price);
        int discountPrice = order.calculatePrice();
        //then
        assertThat(discountPrice).isEqualTo(price - fixDiscount);

    }
}