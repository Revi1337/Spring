package chap02;

import chap02.AppContext;
import chap02.Greeter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    // 4. 스프링이 제공하는 클래스를 이용해서 AppContext 를 읽어와 사용하는 것임.
    public static void main(String[] args) {

        // 5. AnnotationConfigApplicationContext 클래스는 자바 Configuration 에서 정보를 읽어와 Bean 객체를 생성하고 관리하는 역할을 하며 생성자에  @Configuration 이 붙은 클래스를 매개변수로 넣는 것임.
        // AppContext 에 정의한 @Bean 설정 정보를 읽어와 Greeter 객체를 생성하고 초기화함.
        // 스프링의 핵심 기능은 객체를 생성하고 초기화하는 것인데, 이에 대한 기능은 ApplicationContext 라는 인터페이스에 정의되어 있으며 AnnotationConfigApplicationContext 는 ApplicationContext 인터페이스에 대한 구현 클래스임. TODO: p.49
        // BeanFactory(interface) -> ListableBeanFactory(interface) -> ApplicationContext(interface) -> ConfigurableApplicationContext -> AbstractApplicationContext -> GenericApplicationContext -> AnnotationConfigApplicationContext, GenericXmlApplicationContext, GenericGroovyApplicationContext 순 으로 의존함.
        // BeanFactory 인터페이스는 객체 생성과 검색에 대한 기능을 정의. getBean() 메서드가 BeanFactory 에 정의되어 있음. (객체를 검색하는 것 이외에 싱글톤 / 프로토타입 Bean 인지 확인하는 기능도 있음.)
        // ApplicationContext 인터페이스는 메시지, 프로필/환경 변수 등을 처리할 수 있는 기능을 추가로 정의함. 
        // AnnotationConfigApplicationContext 는 자바 클래스에서 정보를 읽어와 객체 생성과 초기화를 수행함. (말단)
        // GenericXmlApplicationContext 는 XML 로부터 객체 설정 정보를 가져옴. (말단)
        // GenericGroovyApplicationContext 는 그루비 코드를 이용해 설정 정보를 갖고옴. (말단)
        // ApplicationContext 또는 BeanFactory 는 빈 객체의 생성, 초기화, 보관, 제거 등을 관리하고 있어서 ApplicationContext 혹은 BeanFactory 를 컨테이너라고 부름.
        // 어떤 구현 클래스를 사용하던 각 구현클래스의 설정 정보로부터 Bean 이라고 불리는 객체를 생성하고 객체를 스프링 컨테이너 내부에 보관하는 것임.
        // 스프링 컨테이너는 내부적으로 빈 객체와 빈 이름을 연결하는 정보를 갖으며, 실제 객체의 생성, 초기화, 의존 주입 등 다양한 기능을 제공함. ( greeter 라는 Bean 이름과 Greeter 객체의 연결 정보를 관리하는 것임. ) TODO: p.50
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppContext.class);

        // 6. getBean() 메서드는 AnnotationConfigApplicationContext 가 자바 Configuration 을 읽어와 생성한 Bean 객체를 검색할 때 사용됨. (첫번째 매개변수에는 Bean 객체의 이름, 두번째 매개변수에는 검색할 빈 객체의 리턴 타입의 의미.)
        // AppContext 에서 Bean 의 리턴타입이 Greeter 이니까 자료형은 Greeter 임.
        Greeter g = ctx.getBean("greeter", Greeter.class);
        
        // 7. Bean 객체의 메서드를 실행중.
        String msg = g.greet("스프링");
        System.out.println(msg);
        ctx.close();
    }
}
