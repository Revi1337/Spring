package config;

import chap09.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    // TODO 3 Controller 로 지정한 class 를 Spring Bean 으로 만들어준다. (HelloController 참고)
    @Bean
    public HelloController helloController() {
        return new HelloController();
    }
}
