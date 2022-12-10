package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc       // TODO 1 스프링 MVC 설정을 활성화, 스프링 MVC 를 사용하는데 필요한 다양한 설정을 생성한다. (스프링 MVC 를 사용하는데 필요한 기본적인 구성을 설정해줌.)
public class MvcConfig implements WebMvcConfigurer {    // TODO 1 WebMvcConfigurer 인터페이스는 스플이 MVC 의 개별 설정을 조정할 때 사용함.

    @Override       // TODO 1 DispatcherServlet 의 매핑 경로를 / 로 주었을 떄 JSP/HTML/CSS 등을 바르게 처리하기 위한 설정을 추가.
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override       // TODO 1 JSP 를 이용해서 컨트롤러의 실행 결과를 보여주기 위한 설정을 추가한다. (JSP 를 위한 것임.)
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }
}
