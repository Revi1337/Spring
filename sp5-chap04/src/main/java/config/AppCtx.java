package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.*;

@Configuration
public class AppCtx {
    // TODO: 주석처리하고 확인하기
    // TODO: 일치하는 Bean 이 없으면 Error creating bean with name '~~~' 오류나
    // TODO: No qualifying bean of type 가 발생.
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePwdSvc() {
        return new ChangePasswordService();
    }

    // TODO: 주석처리하고 확인하기
    // TODO: 일치하는 Bean 이 두개거나 그 이상일때는 "No qualifying bean of type" 에러가 발생
    // TODO: 이 뜻은 해당 타입의 Bean 이 한 개 그 이상이어서 오류가 났단 것임.
    // TODO: 따라서 @Qualifier 어노테이션으로 Bean 의 이름을 따로 명시해야함. (Bean 을 주입받는 곳도 명시한 이름으로 사용해야 함.)
    @Bean
    @Qualifier("printer2")
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    // TODO: 주석처리하고 확인하기
    // TODO: 일치하는 Bean 이 두개거나 그 이상일때는 "No qualifying bean of type" 에러가 발생
    // TODO: 이 뜻은 해당 타입의 Bean 이 한 개 그 이상이어서 오류가 났단 것임.
    // TODO: 따라서 @Qualifier 어노테이션으로 주입할 Bean 의 이름을 따로 명시해야함. (Bean 을 주입받는 곳도 명시한 이름으로 사용해야 함.)
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
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter();
    }

    @Bean
    public MemberInfoPrinter infoPrinter() {
        return new MemberInfoPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }

}
