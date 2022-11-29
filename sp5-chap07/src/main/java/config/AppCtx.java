package config;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.ExeTimeCalculator;
import chap07.ImpleCalculator;
import chap07.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration          // TODO 2
@EnableAspectJAutoProxy // TODO @Aspect 어노테이션을 붙인 클래스를 공통 기능으로 적용하려면 @EnableAspectJAutoProxy 어노테이션을 설정 클래스에 붙여야 함.
public class AppCtx {   // TODO 이 어노테이션을 추가하면 스프링은 @Aspect 어노테이션이 붙은 빈 객체를 찾아서 빈 객체의 @Pointcut 설정과 @Around 설정을 사용함

    // TODO: 이놈을 주석처리하고 확인해보자 (AOP 를 적용하지 않은 경우 : 타입이 RecCalculator 임을 확인 가능하다.)
    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}
