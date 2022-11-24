package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

// TODO: 스프링은 DI 를 이용해 의존객체를 주입하는 Assembler 와 같은 조립기 역할을 대신해줄 수 있다.
// TODO: 즉, Assembler 클래스의 생성자 코드처럼 필요한 객체를 생성하고 생성한 객체에 의존을 주입함.

@Configuration  // 스프링 설정. 이놈을 붙여야 스프링 설정 클래스로 사용 가능.
public class AppCtx {

    // 해당 메서드가 생성한 객체를 이름이 memberDao 라는 스프링 Bean 객체로 등록
    // MemberRegisterService 가 의존하는 객체인 MemberDao 를 memberDao 라는 Bean 으로 등록하는 것임.
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }


    // 해당 메서드가 생성한 객체를 이름이 memberRegSvc 라는 스프링 Bean 객체로 등록
    // 생성자에 전달할 의존 객체가 하나면 아래와 같이 주입하면 됨. TODO (생성자 방식)
    @Bean
    public MemberRegisterService memberRegSvc(){
        return new MemberRegisterService(memberDao());
    }


    // 해당 메서드가 생성한 객체를 이름이 changePwdSvc 라는 스프링 Bean 객체로 등록
    //  TODO (세터 방식): 세터 방식의 규칙은 MemberInfoPrinter 안에 써놓았음.
    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }


    // MemberListPrinter 가 의존하는 객체인 MemberPrinter 를 memberPrinter 라는 Bean 으로 등록하는 것임.
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }


    // 생성자에 전달할 의존 객체가 두개 이상이어도 동일한 방식으로 주입하면 됨. TODO (생성자 방식)
    @Bean
    public MemberListPrinter memberListPrinter(){
        return new MemberListPrinter(memberDao(), memberPrinter());
    }


    //  TODO (세터 방식): 세터 방식의 규칙은 MemberInfoPrinter 안에 써놓았음.
    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao());
        infoPrinter.setPrinter(memberPrinter());
        return infoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }


    // TODO: 설정 클래스를 만들었다고 끝난 것이 아님. 객체를 생성하고 의존 객체를 주입하는 것은 스프링 컨테이너이므로 설정 클래스를 이용해서 컨테이너를 생성해야 함.
    // TODO: chap02 에서 사용한 AnnotationConfigApplicationContext 클래스를 사용해서 스프링 컨테이너를 생성할 수 있음.
}
