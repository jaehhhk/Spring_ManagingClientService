package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    //MemberRepository repository = new MemoryMemberRepository();   // AfterEach 쓰기 전 코드

    MemoryMemberRepository repository = new MemoryMemberRepository(); // AfterEach 쓴 후

    @AfterEach  // 메서드마다 끝나고 정의해 준 메서드 실행되게 -> 테스트 순서 상관없이 실행 가능 -> 공용 데이터 지워줌
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring!");

        repository.save(member);
        Member result =  repository.findById(member.getId()).get();  // optional인 경우 get으로 꺼낼 수 있음(권장은 아님)

        // Test 방법 1
        //System.out.println("result = " + (result == member));

        // Test 방법 2
        //Assertions.assertEquals(member, result); // 같으면 터미널 좌측에 초록색 체크, 아니면 에러 뜸

        // Test 방법 3 assertj 로 TAB
        // Assertions.assertThat(member).isEqualTo(result); 여기서 Assertions부분에 alt+enter 누르면 static으로 임포트돼서 Assert 없이 바로 이용 가능
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift + f6으로 하면 한번에 변수 바꿀 수 있음
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

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

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);  // 사이즈 2개 나와야
    }


}
