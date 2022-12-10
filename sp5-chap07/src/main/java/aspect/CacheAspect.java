package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.HashMap;
import java.util.Map;

@Order(2)       // TODO Oder() 어노테이션으로 AOP 적용순서를 지정할 수 있음.
@Aspect
public class CacheAspect {

    private Map<Long, Object> cache = new HashMap<>();

    @Pointcut("execution(public * chap07..*(long))")    // 공통기능을 정의하는 Pointcut 설정은 첫번쨰 인자가 long 인 메서드를 대상으로 지정했다.
    public void cacheTarget() {
    }

    @Around("cacheTarget()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Long num = (Long) joinPoint.getArgs()[0];           // 첫번쨰 인자를 Long 타입으로 형변환한다.

        if(cache.containsKey(num)){                         // 구한 키(num) 값이 cache 에 존재하면 키에 해당하는 값을 구해서 리턴한다.
            System.out.printf("CacheAspect: Cache에서 구함 [%d]\n", num);
            return cache.get(num);                          // TODO 이 경우 joinPoint.proceed() 를 실행하지 않으므로 ExeTimeAspect 나 실제 객체가 실행되지 않음.
        }

        Object result = joinPoint.proceed();                // 프록시 대상 객체를 실행
        cache.put(num, result);                             // 프록시 대상 객체를 실행한 결과를 cache 에 추가한다.
        System.out.printf("CacheAspect: Cache에 추가 [%d]\n", num);
        return result;
    }

}
