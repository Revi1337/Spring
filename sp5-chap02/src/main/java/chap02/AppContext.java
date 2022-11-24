package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 1. @Configuration 에노테이션은 해당 클래스를 스프링 설정 클래스로 지정하는 것임.
public class AppContext {

    // 2. 스프링은 객체를 생성하고 초기화하는 기능을 제공하는데 (13 ~ 18) 가 한개의 객체를 생성하고 초기화하는 설정을 담고 있음.
    // 스프링이 생성하는 객체를 빈(Bean) 객체라고 부르는데, 이 빈 객체에 대한 정보를 담고 있는 메서드가 greeter() 메서드임.
    // @Bean 어노테이션을 붙이면 해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록하는 것임.

    // 3. @Bean 어노테이션을 붙인 메서드의 이름은 Bean 객체를 구분하고 사용됨. 구분할때는 @Bean 어노테이션이 붙어진 메서드드 이름 = greeter 로 구분함. 이 Bean 이름은 빈 객체를 참조할 떄 사용됨.
    // @Bean 어노테이션을 붙인 메서드는 객체를 생성하고 알맞게 초기화해야 함. 17 ~ 18 에서 Greeter 객체를 초기화하고 있는 것임.
    @Bean
    public Greeter greeter() {
        Greeter g = new Greeter();
        g.setFormat("%s, 안녕하세요!");
        return g;
    }

    @Bean
    public Greeter greeter1() {
        Greeter g = new Greeter();
        g.setFormat("안녕하세요, %s 님!");
        return g;
    }

}
