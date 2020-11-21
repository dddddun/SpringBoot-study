package woodangtang.woodangtangspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import woodangtang.woodangtangspring.domain.Member;
import woodangtang.woodangtangspring.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // dependency injection (의존성 주입) : 내부에서 만든 변수를 외부에서 넣어줌
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    // 회원가입 성공
    void join() {
        // given (상황이 주어짐)
        Member member = new Member();
        member.setName("hello");

        // when (이것을 실행했을 때)
        Long saveId = memberService.join(member);

        //then (이런 결과가 나와야함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // memberService.join(member2) 로직이 실행되면 IllegalStateException.class가 실행되어야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        /*
       애매한 문법. 좋은 문법은 위와 같다.
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
 */

        // 출력 문자 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



        // then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}