package com.example.core.discount;

import com.example.core.member.Grade;
import com.example.core.member.Member;

public class FixedRatePolicy implements DiscountPolicy{
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price/10;
        }
        return 0;
    }
}
