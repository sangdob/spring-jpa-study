package jpa.shop.service;

import jpa.shop.domain.Member;
import jpa.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * requiredArgsConstructor final 대상으로 생성자를 작성해줌!!
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public int findMembersCount() {
        return memberRepository.findByMembersOfCount();
    }

    //   회원가입
    @Transactional
    public Long join(Member member) {
//      중복회원 검색
        validateDuplidateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplidateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findByMember(Long id) {
        return memberRepository.findByMember(id);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findByMember(id);
        member.setName(name);
    }
}
