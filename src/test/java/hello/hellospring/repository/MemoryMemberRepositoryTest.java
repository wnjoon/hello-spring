package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트의 순서는 고정되어있지 않음 + 테스트 단계마다 스프링에 데이터를 저장
    // 테스트가 끝날때마다 테스트에 사용된 데이터를 삭제해주어야 함 (의존관계를 없애줌)
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member newMember = repository.findById(member.getId()).get();  // Member 객체 바로 반환
        Assertions.assertEquals(member, newMember);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member newMember1 = repository.findByName("spring").get();
        Assertions.assertEquals(newMember1, member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> list = repository.findAll();
        Assertions.assertEquals(list.size(), 2);
    }
}
