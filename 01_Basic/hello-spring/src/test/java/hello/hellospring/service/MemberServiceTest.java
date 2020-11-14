package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemoryMemberRepository memoryMemberRepository;
    MemberService service;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        service = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

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

//        service.join(member1);
//        try {
//            service.join(member2);
//            fail();
//        } catch(Exception e){
//            assertThat(e.getMessage()).isEqualTo("The username is already exist");
//        }

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}