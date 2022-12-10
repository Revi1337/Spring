package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.ChangePasswordService;
import spring.MemberDao;

// TODO @EnableTransactionManagement 어노테이션은 @Transactional 어노테이션이 붙은 메서드를 트랜잭션 범위에서 실행하는 기능을 활성화함.
// TODO 등록된 PlatformTransactionManager Bean 을 사용해서 트랜잭션을 적용하는 것임.
// TODO 트랜잭션 처리를 위한 설정을 완료하면 트랜잭션 범위에서 실행하고 싶은 스프링 Bean 객체의 메서드에 @Transactional 어노테이션을 붙이면 됨.
// TODO chagnePasswordService 참고.
@EnableTransactionManagement
@Configuration
public class AppCtx {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8&autoReconnect=true&useSS=false");
        ds.setUsername("spring5");
        ds.setPassword("spring5");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setMaxIdle(10);
        return ds;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }

    // TODO PlatformTransactionManager 는 스프링이 제공하는 트랜젝션매니저 인터페이스임. 스프링은 구현기술에 상관없이 동일한 트랜잭션을
    // TODO 처리하기 위해 이 인터페이스를 사용. JDBC 는 DataSourceTransactionManager 클래스를  PlatformTransactionManager 로 사용함.
    // TODO dataSource() 프로퍼티를 이용해서 트랜잭션 연동에 사용할 DataSource 를 지정함.
    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    // TODO @Transactional 이 붙은 객체를 Bean 객체로 만듬
    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }


}
