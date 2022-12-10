package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.Member;
import spring.MemberDao;
import spring.MemberPrinter;

// AppCtx 와 다를게 없음. 중요한건 AppConf2 클래스임.

@Configuration
public class AppConf1 {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
}
