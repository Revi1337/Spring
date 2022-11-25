package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.*;

// TODO 2
// TODO @Component 애노테이션을 붙인 클래스를 스캔해서 스프링 빈으로 등록하려면 설정 클래스에 @ComponentScan 어노테이션 을 적용해야 함.
//  (basePackages 속성은 스캔 대상 패키지 목록을 지정함 : spring 패키지 와 그 하위 패키지에 속한 클래스를 스캔 대상으로 설정하는 것이며 @Component 어노테이션이 붙은 클래스의 객체를 생성해서 빈으로 등록함.)
// TODO 특정 클래스를 컴포넌트 스캔 대상에서 제외하거나 포함할떄는 아래와 같이 excludeFilter 속성을 사용함.
//@ComponentScan(basePackages = {"spring"}, excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "spring\\..*Dao"))
@ComponentScan(basePackages = {"spring"})
@Configuration
public class AppCtx {

    @Bean
    public Client client() {
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
