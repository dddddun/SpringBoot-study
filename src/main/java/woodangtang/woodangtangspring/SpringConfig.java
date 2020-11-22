package woodangtang.woodangtangspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woodangtang.woodangtangspring.repository.MemberRepository;
import woodangtang.woodangtangspring.repository.MemoryMemberRepository;
import woodangtang.woodangtangspring.service.MemberService;

/** 직접 스프링 빈을 등록하는 방법 **/

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    // 현재 DB를 지정하지 않고 이후에 DB를 등록할 것 이기 때문에
    // Bean을 직접 등록해놓고 수정하는게 편리하다.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
