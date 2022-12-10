package config;

import org.springframework.context.annotation.Import;
import spring.MemberInfoPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

// TODO: 꼭 읽어라
// @Autowired 어노테이션은 스프링의 자동 주입 기능을 위한 어노테이션임. 주입이라는 단어에서 눈치챌 수 있지만, 이설정은 DI 와 관련이 있다.
// 스프링 설정 클래스 필드에 @Autowired 어노테이션을 붙이면 해당 타입의 빈을 찾아서 필드에 할당함.
// 즉, 아래의 @Autowired 가 달려있는 MemberDao 타입의 Bean 을 찾아서 필드에 할당한다는 소리이며, 이는 MemberPrinter 도 마찬가지이다.
// 아래의 설정같은 경우에는 AppConf1 클래스에 MemberDao 타입의 memberDao 라는 이름의 Bean 객체 설정했으므로 AppConf2 클래스의 memberDao 필드에는 AppConf1 클래스에서 memberDao Bean 이 할당되는 것이다.

// 주의할 점은 @Autowired 어노테이션을 이용해서 다른 설정 파일(AppConf1)에 정의된 빈을 필드에 할당했다면, 설정 메서드(AppConf2)에서 이 필드를 사용해서 필요한 빈을 주입하면 된다.
// 42 ~ 45 라인이 필드로 주입받은 빈 객체를 생성자를 이용해서 주입하고 있는 것임.

// 설정 클래스가 두 개 이상이어도 스프링 컨테이너를 생성하는 코드는 크게 다르지 않음. 아래와 같이 파라미터로 설정 클래스를 추가로 전달해주면 됨.
// ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);  ->  MainForSpring2 참고

@Configuration
@Import(AppConf1.class)
public class AppConf2 {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberPrinter memberPrinter;

    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberDao);
    }

    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
        return pwdSvc;
    }

    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter(memberDao, memberPrinter);
    }

    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao);
        infoPrinter.setPrinter(memberPrinter);
        return infoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
