package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Order(1)       // TODO Oder() 어노테이션으로 AOP 적용순서를 지정할 수 있음.
@Aspect
public class ExeTimeAspect {

    // TODO chap07 패키지와 그 하위 패키지에 위치한 타입의 public 메서드를 Pointcut 으로 설정 (공통 기능을 적용할 대상 설정)
    @Pointcut("execution(public * chap07..*(..))")
    private void publicTarget() {
    }

    // TODO publicTarget() 메서드에 정의한 Pointcut 에 공통 기능을 적용한다는 뜻
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable { // ProceedingJoinPoint 타입 파라미터는 프록시 대상 객체의 메서드를 호출할 떄 사용.
        long start = System.nanoTime();                                     // ProceedingJoinPoint 의 proceed() 메서드를 사용해서 실제 대상 객체의 메서드를 호출함.
        try{                                                                // 이 메서드를 호출하면 대상 객체의 메서드가 실행되므로 이 코드 이전과 이후에 공통 기능을 위한 코드를 위치시키면 됨.
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();                       // ProceedingJoinPoint 의 getSignature(), getTarget(), getArgs() 메서드는 호출한 메서드의 시그니처, 대상 객체, 인자 목록을 구하는데 사용됨.
            System.out.printf("[Around] %s.%s(%s) 실행 시간 : %d ns\n",      // 자바에서 메서드 이름과 파라미터를 합쳐서 메서드 시그니처라고도 함. 메서드 이름이 다르거나 파라미터 타입, 개수가 다르면 시그니처가 다르다고 표현함. 자바에서 메서드의 리턴 타입이나 익셉션 타입은 시그니처에 포함되지 않음.
                    joinPoint.getTarget().getClass().getSimpleName(),       // Signature getSignature() : 호출되는 메서드에 대한 정보를 구한다, Object getTarget() : 대상 객체를 구한다, Object[] getArgs() : 메서드의 파라미터 목록을 구한다.
                    sig.getName(), Arrays.toString(joinPoint.getArgs()),
                    (finish - start));
        }
    }

}
