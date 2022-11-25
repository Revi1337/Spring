package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// TODO 3
// TODO 스프링 컨테이너는 빈 객체의 라이프 사이클을 관리함. 컨테이너가 관리하는 빈 객체의 라이프 사이클의 순서는 객체생성 -> 의존 설정 -> 초기화 -> 소멸 임
// TODO 스프링 컨테이너를 초기화할 때 스프링 컨테이너는 가장 먼저 빈 객체를 생성하고 의존을 설정함. 의존 자동 주입을 통한 의존 설정이 이 시점에 수행됨.
// TODO 모든 의존 설정이 완료되면 빈 객체의 초기화를 수행하는데, 빈 객체를 초기화하기 위해 스프링은 빈 객체의 지정된 메서드를 호출함
// TODO 스프링 컨테이너를 종료하면 스플이 컨테이너는 빈 객체의 소멸을 처리함. 이떄에도 지정한 메서드를 호출함.
// TODO 스프링 컨테이너느 빈 객체를 초기화하고 소멸하기 위해 빈 객체의 지정한 메서드를 호출하는데. 이 메서드는 InitializingBean , DisposableBean 인터페이스 안에 있음
// TODO 빈 객체가 InitializingBean 인터페이스를 구현하면 스프링 컨테이너는 초기화 과정에서 빈 객체의 afterPropertiesSet() 메서드를 실행함. 빈 객체를 생성한 뒤에 초기화 과정이 필요하면 InitializingBean 인터페이스를 상속하고 afterPropertiesSet() 메서드를 알맞게 구현하면 됨.
// TODO 빈 객체가 DisposableBean 인터페이스를 구현한 경우, 소멸 과정에서 destroy() 메서드를 알맞게 구현하면 됨.
// TODO 이렇게 설정하고 설정클래스에서 Bean 을 등록하면 됨. (AppCtx 참조)

public class Client implements InitializingBean, DisposableBean {

    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Client.afterPropertiesSet() 실행");
    }

    public void send() {
        System.out.println("Client.send() to" + host);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Client.destroy() 실행");
    }

}
