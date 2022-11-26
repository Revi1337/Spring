package com.example.aop2.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.example.aop2.controller..*.*(..))")
    private void cut(){
    }

    @Pointcut("@annotation(com.example.aop2.annotation.Timer)")
    private void enableTimer() {
    }

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[Called @Around Annotation]");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // TODO: proceed() 메서드를 수행해야 Controller 의 delete() 메서드가 수행되는 것임. (즉, @Around 는 Controller 보다 먼저 수행되는것임.)
        // TODO: 따라서 암호화된 문자를 비즈니스코드에서 복호화하는 것이 아니라 AOP 단에서 암호화된 문자를 복호화하여 처리할 수 있음. (DecodeAop 참고)
        Object object = joinPoint.proceed();

        stopWatch.stop();
        System.out.println("Total Task Time : " + stopWatch.getTotalTimeSeconds());
        System.out.println();
    }
}
