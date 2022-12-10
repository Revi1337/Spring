package main;


import chap07.Calculator;
import chap07.RecCalculator;
import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

//        Calculator cal = ctx.getBean("calculator", RecCalculator.class);
        Calculator cal = ctx.getBean("calculator", Calculator.class);
        long fiveFact = cal.factorial(5);
        System.out.println("[main] cal.factorial(5) = " + fiveFact);
        System.out.println("[main] " + cal.getClass().getName());
        ctx.close();

    }
}