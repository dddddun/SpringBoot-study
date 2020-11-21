package woodangtang.woodangtangspring.repository;

import org.springframework.stereotype.Repository;
import woodangtang.woodangtangspring.domain.Member;

import java.util.*;

@Repository
public class MemoryMemberRepository implements  MemberRepository{

    // save를 위해 생성 <회원아이디, 값>
    // Map을 사용하면 동시성 문제가 있을 수 있지만 예제이므로 그냥 사용
    private static Map<Long, Member> store = new HashMap<>();

    // 키값을 생성해줌
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return  member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null값이 있을 경우 Optional.ofNullable로 감싸서 반환한다.
        return  Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 루프
        return store.values().stream()
                // (람다) 멤버에서 멤버.getName이 파라미터로 넘어온 name과 같으면 필터링
                .filter(member -> member.getName().equals(name))
                // 찾으면 반환. 루프가 다 돌아도 없으면 optional로 감싸서 반환.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // 맵을 리스트로 반환. store.values() = Member
        return new ArrayList<>(store.values());
    }

    // store(저장소)를 비우는 메서드
    public  void clearStore() {
        store.clear();
    }
}
