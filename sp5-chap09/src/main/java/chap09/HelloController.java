package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO 2 Controller 를 설정한다.
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model, // TODO Model 파라미터는 컨트롤러의 처리 결과를 뷰에 전달할 떄 사용함.
                        @RequestParam(value = "name", required = false) String name) {

        // TODO addAddtribute() 는 뷰에 전달한 데이터를 지정하기 위해 사용됨. greeting 이라는 모델에 "안녕하세요" + name 값을 연결한 문자열 값을 설정하는 것임.
        // TODO 걍 jsp 파일에서 ${greeting} 의 값을  "안녕하세요" + name 로 설정해주겠다는 것임. (모델은 ${} 안에 표현됨.)
        model.addAttribute("greeting", "안녕하세요, " + name);

        return "hello"; // TODO 컨트롤러의 처리 결과를 보여줄 뷰 이름으로 "hello" 를 사용함. (이 뷰 이름은 논리적인 이름이며 실제로 뷰 이름에 해당하는 뷰 구현을 찾아주는 것은 ViewResolver 가 처리함.)

        // TODO 컨트롤러를 구현했다면 스프링 빈으로 등록해야함. (ControllerConfig 참고)
    }
}
