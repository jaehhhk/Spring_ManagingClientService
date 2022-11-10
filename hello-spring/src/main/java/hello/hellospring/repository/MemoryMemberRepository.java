package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //아이디 1 증가해서 세팅
        store.put(member.getId(), member);  // map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // null값 나오는 것 대비해 optional로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 자바 람다함수
        // 루프 돌면서 하나 찾아지면 반환 만약 없으면 null이 optional에 감싸져서 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store 비워주는 메서드
    public void clearStore() {
        store.clear();
    }
}
