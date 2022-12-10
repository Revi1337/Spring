package main;

import chap07.ExeTimeCalculator;
import chap07.ImpleCalculator;
import chap07.RecCalculator;

// TODO 1
// TODO 이렇게 핵심 기능의 실행은 다른 객체에 위임하고 부가적인 기능을 제공하는 객체를 프록시라고 부름. 실제 핵심 기능을 실행하는 객체는 대상 객체라고 부름. 따라서 ExeTimeCalculator 가 프록시이고 ImpeCalculator 객체가 프록시의 대상 객체가 됨.
// TODO 이렇게 공통 기능 구현과 핵심 기능을 분리하는 것이 AOP 의 핵심임. (Aspect Oriented Programming : 여러 객체에 공통으로 적용할 수 있는 기능을 분리해서 재사용성을 높여주는 프로그래밍 기법임.)
// TODO AOP 의 기본개념은 핵심 기능에 공통 기능을 삽입하는 것임. 즉, 핵심 기느으이 코드를 수정하지 않으면서 공통 기능의 구현을 추가하는 것이 AOP 임. 핵심 기능에 공통 기능을 삽입하는 방법에는 세가지가 있음.
// 1. 컴파일 시점에 코드에 공통 기능을 삽입하는 방법
// 2. 클래스 로딩 시점에 바이트 코드에 공통 기능을 삽입하는 방법
// 3. 런타임에 프록시 객체를 생성해서 공통 기능을 삽입하는 방법

// TODO 1, 2 번과 같은 방법은 스프링 AOP 에서는 지원하지 않으며 AspectJ 와 같이 AOP 전용 도구를 사용해서 적용할 수 있음
// TODO 스프링에서 제공하는 AOP 방식은 프록시를 이용한 세번쨰 방식임. (해당 클래스에와 같이 중간에 프록시 객체를 생성하는 것이며 실제 객체의 핵심 기능을 실행하기 전.후에 공통 기능을 호출함.)
// TODO AOP 에서 공통 기능을 Aspect 라고 하는데 Aspect 외에 알아두어야 하는 용어는 아래와 같음
// 1. Advice : 언제 공통 관심 기능을 핵심 로직에 적용할 지를 정의하고 있음. 예를 들어 '메서드를 호출하기 전(언제)'에 '트랜잭션 시작(공통기능)' 기능을 적용한다는 것을 정의함.
// 2. Joinpoint : Advice 를 적용 가능한 지점을 의미함. 메서드 호출, 필드 값 변경 등이 Joinpoint 에 해당됨. 스프링은 프록시를 이용해서 AOP 를 구현하기 때문에 메서드 호출에 대한 Joinpoint 만 지원함.
// 3. Pointcut : Jointpoint 의 부분집합으로서 실제 Advice 가 적용되는 JointPoint 를 나타냄. 스프링에서는 정규 표현식이나 AspectJ 의 문법을 이용하여 Pointcut 을 정의할 수 있음.
// 4. Weaving : Advice 를 핵심 로직 코드에 적용하는 것을 weaving 이라고 함.
// 5. Aspect : 여러 객체에 공통으로 적용되는 기능을 Aspect 라고함. 트랜젝션이나 보안 등이 Aspect 의 좋은 예임. (클래스에 붙힘)

// TODO 스프링에서 구현 가능한 Advice (시점)
// Before Advice : 대상 객체의 메서드 호출 전에 공통 기능을 실행
// After Returning Advice : 대상 객체의 메서드가 익셉션 없이 실행된 이후에 공통된 기능을 실행.
// After Throwing Advice : 대상 객체의 메서드를 실행하는 도중 익셉션이 발생한 경우에 공통 기능을 시행
// After Advice : 익셉션 발생 여부에 상관없이 대상 객체의 메서드 실행 후 공통 기능을 실행 (try catch finally 의 finally 과 비슷)
// Around Advice : 대상 객체의 메서드 실행 전, 후 또는 익셉션 발생 시점에 공통 기능을 실행하는데 사용됨.

// TODO AOP 구현 순서
// 1. Aspect 로 사용할 클래스에 @Aspect 애노테이션을 붙이기
// 2. @Pointcut 어노테이션으로 공통 기능을 적용할 Pointcut 을 정의
// 3. 공통 기능을 구현한 메서드에 @Around 어노테이션을 적용.

public class MainProxy {

    public static void main(String[] args) {
        ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpleCalculator());
        System.out.println(ttCal1.factorial(20));


        ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
        System.out.println(ttCal2.factorial(20));
    }
}
