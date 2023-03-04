package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //제약조건 unique로 설정하여 최후의 방어선 만들기
    private void validateDuplicateMember(Member member) {

        List<Member> memberList = memberRepository.findByName(member.getName());
        if (!memberList.isEmpty()) {
            throw new IllegalStateException("중복되는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 한 건만 조회
    public Member findMember(Long MemberId) {
        return memberRepository.findOne(MemberId);
    }


}
