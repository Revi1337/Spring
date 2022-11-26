package com.example.aop2.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParmeterAop {

    @Pointcut("execution(* com.example.aop2.controller..*.*(..))")
    private void cut() {
    }

    @Before("cut()")
    private void before(JoinPoint joinPoint) {
        System.out.println("==========================================");
        System.out.println("[Called @Before Annotation]");
        Object[] args = joinPoint.getArgs();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("Called Method : " + method.getName());

        for(Object obj : args){
            System.out.printf("Argument : (%s) %s\n", obj.getClass().getSimpleName(), obj);
        }

        System.out.println();
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    private void afterReturn(JoinPoint joinPoint, Object returnObj) throws JsonProcessingException {
        System.out.println("[Called @AfterReturning Annotation]");
        System.out.println(returnObj);
        System.out.println("==========================================");
        System.out.println();
    }
}
