package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {
        // Dependency Injection
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 테스트는 한글로 적어도 무관하다
        //given (주어진 것)
        Member newMember = new Member();
        newMember.setName("hello");

        //when (실행)
        Long newMemberId = memberService.join(newMember);

        //then (결과)`
        Member findMember = memberService.findOne(newMemberId).get();
        Assertions.assertEquals(findMember.getName(), newMember.getName());

    }

    @Test
    void 중복회원예외() {
        //given
        Member member1 = new Member();
        member1.setName("man");
        Member member2 = new Member();
        member2.setName("man");

        //when

        // 1. try-catch 사용시
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다");
//        }
        // 2. 다른 방법
        Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}