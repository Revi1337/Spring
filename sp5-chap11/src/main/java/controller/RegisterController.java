package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.DuplicateMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;


@Controller
@RequestMapping("/register")
public class RegisterController {

    @RequestMapping("/step1")
    public String handleStep1() {
        return "register/step1";
    }


    @GetMapping("/step2")
    public String handleStep2Get(){
        return "redirect:/register/step1";
    }


    // TODO 1 파라미터 접근 (@RequestParam 이용. value=<String> : HTTP 요청 파리미터의 이름 지정, required=<boolean> : 필수여부 지정, defaultValue=<String> : 요청 파라미터 값이 없을 떄 사용할 문자열 값을 정함.)
    @PostMapping("/step2")
    public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, Model model) {
        if(!agree)
            return "redirect:/register/step1";
        model.addAttribute("formData", new RegisterRequest());
        return "register/step2";
    }

    // TODO 2 스프링은 파라미터의 값을 커맨드(command) 객체에 담아주는 기능을 제공함. 따라서 파라미터가 많을 때 일일이 값을 설정해주지 않게해줌. (커맨드 객체는 파라미터의 값을 세터 메서드 통해 값을 전달하는 객체를 말함.)
    // TODO 2 예를들면 name 이라는 파라미터 값을 커맨드 객체의 setName() 메서드를 사용해서 커맨드 객체에 전달하는 기능을 제공함. (즉, 매개변수에 커맨드 객체를 넣어주면 됨.)
    private MemberRegisterService memberRegisterService;
    public void setMemberRegisterService(MemberRegisterService memberRegisterService){
        this.memberRegisterService = memberRegisterService;
    }
    @PostMapping("/step3")
    public String handleStep3(@ModelAttribute(value = "formData") RegisterRequest registerRequest){ // TODO @ModeAttribute 어노테이션으로 커맨드 객체 속성 이름 변경 (view 코드에서 formData 이름으로 커맨드 객체에 접근이 가능하다.)
        try {
            memberRegisterService.regist(registerRequest);
            return "register/step3";
        } catch (DuplicateMemberException ex){
            return "register/step2";
        }
    }

}
