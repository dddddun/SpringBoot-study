package woodangtang.woodangtangspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import woodangtang.woodangtangspring.domain.Member;

import java.awt.*;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test가 끝날 때 마다 지워주는 코드를 넣어야함
    // 메서드가 끝날 때 마다 동작을 함
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public  void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result =" + (result == member));

        // (junit) 실행되면 같고 오류가 생기면 에러가 생김
        // Assertions.assertEquals(member, result);

        // (assertj) 멤버가 결과와 같은가.
        // Assertions.assertThat(member).isEqualTo(result); 에서 alt+enter
        // Add on-demand static import 하면 assertThat()을 쉽게 쓸 수 있다.
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("string1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result =  repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
