package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 중복회원은 안된다
        // 단출어: cmd+option+v -> 리턴되는 변수를 알아서 빼줌
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // command+option+m -> 블록처리된 부분만큼을 별도의 함수로 extract 해줌
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                // ifPresent -> null이 아닌지 확인 (Optional이라서 가능)
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회(by id)
     */
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}
