package chap07;

public class RecCalculator implements Calculator{

    // TODO 1
    // TODO 아래의 펙토리얼 메서드를 3번하면 sout 메서드가 세번 실행됨. 이런 코드 중복을 피할 수 있는 방법은 프록시 객체를 만드는 것임
    // TODO 이렇게 핵심 기능의 실행은 다른 객체에 위임하고 부가적인 기능을 제공하는 객체를 프록시라고 부름. 실제 핵심 기능을 실행하는 객체는 대상 객체라고 부름. 따라서 ExeTimeCalculator 가 프록시이고 ImpeCalculator 객체가 프록시의 대상 객체가 됨.

    @Override
    public long factorial(long num) {

        long start = System.currentTimeMillis();

        try{
            if (num == 0)
                return 1;
            else
                return num * factorial(num - 1);
        } finally {
            long end = System.currentTimeMillis();
            // System.out.printf("RecCalculator.factorial(%d) 실행 시간 = %d\n", num, (end - start));
        }

    }
}
