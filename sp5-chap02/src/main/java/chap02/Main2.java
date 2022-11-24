package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);

        // 8. Bean 객체를 2개 두해서 비교하면 같다는 것을 알 수 있음
        // 즉, 별도의 설정이 없으면 스프링은 하나의 Bean 객체를 생성하며, 이 때 Bean 객체는 싱글톤 범위를 갖는다라고 표현함.
        // 싱글톤은 단일 객체(Single Object) 를 의미하는 단어로서 스프링은 기본적으로 한 개의 @Bean 어노테이션에 대해 한개의 빈 객체를 생성함.
        Greeter g1 = ctx.getBean("greeter", Greeter.class);
        Greeter g2 = ctx.getBean("greeter", Greeter.class);
        System.out.println("(g1 == g2) = " + (g1 == g2));

        // 9. Bean 객체를 따로 만들어주려면 다음과 같은 설정을 하면 g3 과 g4 에 대한 Bean 객체가 따로 따로 생성된다. TODO: AppContext 의 greeter1 Bean 참고
        Greeter g3 = ctx.getBean("greeter", Greeter.class);
        Greeter g4 = ctx.getBean("greeter1", Greeter.class);
        System.out.println("(g3 == g4) = " + (g3 == g4));

        ctx.close();
    }
}
