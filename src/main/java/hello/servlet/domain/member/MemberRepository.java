package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    // 하나만 사용, 싱글톤으로 사용  -> static 없어도 보장되나 여기서는 사용
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance() {
        return instance;
    }
    private MemberRepository() {
    }
    // 저장 ..
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id) { return store.get(id);
    }
    public List<Member> findAll() {
        // store에 있는 value list를 직접 건들지 않기 위함.. store 보호 목적
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}