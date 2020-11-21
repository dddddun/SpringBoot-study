package woodangtang.woodangtangspring.repository;

import woodangtang.woodangtangspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // optional은 자바8에 들어간 기능
    // null일경우 optional로 감싸서 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
