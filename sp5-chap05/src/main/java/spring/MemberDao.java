package spring;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// TODO 1
// TODO 자동주입과 함께 하는 추가 기능이 컴포넌트 스캔. 컴포넌트 스캔은 스프링이 직접 클래스를 검색해서 빈으로 등록해주는 기능임.
// TODO 설정 클래스에 빈으로 등록하지 않아도 원하는 클래스를 빈으로 등록할 수 있으므로 컴포넌트 스캔 기능을 사용하면 설정 코드가 크게 줄어드는 장점이 있음.
// TODO 스프링이 검색해서 Bean 으로 등록할 수 있으면 클래스에 @Component 애노테이션을 붙여야함.
// TODO @Component 애노테이션은 해당 클래스를 스캔 대상으로 표시하는 것임.

@Component
public class MemberDao {
    private static long nextId = 0;

    private Map<String, Member> map = new HashMap<>();

    public Member selectByEmail(String email) {
        return map.get(email);
    }

    public void insert(Member member) {
        member.setId(++nextId);
        map.put(member.getEmail(), member);
    }

    public void update(Member member) {
        map.put(member.getEmail(), member);
    }

    public Collection<Member> selectAll() {
        return map.values();
    }
}
