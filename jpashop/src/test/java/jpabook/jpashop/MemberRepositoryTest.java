package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void saveTest() {
        //given
        Member member = new Member();
        member.setUsername("spring");

        //when
        Long saveId = memberRepository.save(member);
        Member memberSaved = memberRepository.find(saveId);

        //then
        assertThat(member).isEqualTo(memberSaved);


    }
}