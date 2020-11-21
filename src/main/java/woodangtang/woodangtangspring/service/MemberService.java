package woodangtang.woodangtangspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woodangtang.woodangtangspring.domain.Member;
import woodangtang.woodangtangspring.repository.MemberRepository;
import woodangtang.woodangtangspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

// 순수 자바 클래스면 스프링이 모른다. 어노테이션을 사용해 service임을 표시해줌
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입. 이름이 같으면 안된다.
     */

    public  Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     *  전체 화면 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
