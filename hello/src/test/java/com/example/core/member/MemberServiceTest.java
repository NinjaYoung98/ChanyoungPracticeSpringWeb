package com.example.core.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private final MemberService memberService = new MemberServiceImpl();


    @Test
    void givenMember_WhenFindId_ThenReturnMember() {
        //given
        Member member1 = new Member(1L, "spring", Grade.BASIC);

        //when
        memberService.join(member1);
        Member member2 = memberService.findMember(1L);

        //then
        assertThat(member1).isEqualTo(member2);

    }
}