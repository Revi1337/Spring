package com.example.aop2.aop;

import com.example.aop2.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.aop2.controller..*.*(..))")
    private void cut(){
    }

    @Pointcut("@annotation(com.example.aop2.annotation.Decode)")
    private void enableDecode() {
    }

    // TODO: 실제적인 컨트롤러에서는 User 의 Email 을 복호화할 일이 없음.  (AOP 단에서 복호화해서 보내주어, 실제적인 컨트롤러에서는 복호화된 email을 받아보는 것임.)
    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
            if(arg instanceof User){
                User user = User.class.cast(arg);
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email.getBytes()), "UTF-8");
                user.setEmail(email);
            }
        }
    }

    // TODO: @before 와 반대로 컨트롤러단에서 email 을 인코딩지않고, AOP 단에서 email 을 인코딩하여 컨트롤러단의 부하를 줄일 수 있음.
    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        if(returnObj instanceof User){
            User user = User.class.cast(returnObj);
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }
    }

}
