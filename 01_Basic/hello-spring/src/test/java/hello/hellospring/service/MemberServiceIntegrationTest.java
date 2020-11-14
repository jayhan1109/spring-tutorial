package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService service;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring2");

        // when
        Long saveId = service.join(member);

        // then
        Member foundMember = service.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(foundMember.getName());
    }

    @Test
    public void duplicateMember() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        service.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
        assertThat(e.getMessage()).isEqualTo("The username is already exist");
    }

}