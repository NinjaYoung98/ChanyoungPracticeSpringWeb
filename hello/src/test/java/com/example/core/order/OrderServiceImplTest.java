package com.example.core.order;

import com.example.core.AppConfig;
import com.example.core.discount.DiscountPolicy;
import com.example.core.discount.FixDiscountPolicy;
import com.example.core.member.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();
    private final int price = 20000;

    @Test
    @DisplayName("VIP는 10%할인이 되어야 한다.")
    void order() {
        //given
        Member member = new Member(1L, "ccy", Grade.VIP);
        //when
        memberService.join(member);
        Order order = orderService.createOrder(1L, "맥북", price);
        int discountPrice = order.calculatePrice();
        //then
        assertThat(discountPrice).isEqualTo(price - price / 10);
    }

    @Test
    @DisplayName("일반멤버는 할인이 적용되어선 안된다.")
    void order2() {
        //given
        Member member = new Member(1L, "ccy", Grade.BASIC);
        //when
        memberService.join(member);
        Order order = orderService.createOrder(1L, "맥북", price);
        int discountPrice = order.calculatePrice();
        //then
        assertThat(discountPrice).isEqualTo(price);
    }
}
