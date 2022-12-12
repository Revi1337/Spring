package config;

import controller.HelloController;
import controller.RegisterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberRegisterService;

@Configuration
public class ControllerConfig {

    @Bean
    public HelloController helloController() {
        return new HelloController();
    }

    @Autowired
    private MemberRegisterService memberRegisterService;

    @Bean
    public RegisterController registerController() {
        RegisterController controller = new RegisterController();
        controller.setMemberRegisterService(memberRegisterService);
        return controller;
    }

}
