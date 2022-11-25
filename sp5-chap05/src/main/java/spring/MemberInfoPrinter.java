package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
// TODO 1
// TODO 자동주입과 함께 하는 추가 기능이 컴포넌트 스캔. 컴포넌트 스캔은 스프링이 직접 클래스를 검색해서 빈으로 등록해주는 기능임.
// TODO 설정 클래스에 빈으로 등록하지 않아도 원하는 클래스를 빈으로 등록할 수 있으므로 컴포넌트 스캔 기능을 사용하면 설정 코드가 크게 줄어드는 장점이 있음.
// TODO 스프링이 검색해서 Bean 으로 등록할 수 있으면 클래스에 @Component 애노테이션을 붙여야함.
// TODO @Component 애노테이션은 해당 클래스를 스캔 대상으로 표시하는 것임.

// TODO @Component 애노테이션에 값을 주지 않으면, 클래스 이름의 첫 글자를 소문자로 바꾼 이름을 Bean 이름으로 사용함., 하지만 이번에는 속성을 주었기 때문에 listPrinter 이름의 Bean 이름을 사용함.
@Component("infoPrinter")
public class MemberInfoPrinter {

    private MemberDao memDao;
    private MemberPrinter printer;

    public void printMemberInfo(String email) {
        Member member = memDao.selectByEmail(email);
        if (member == null) {
            System.out.println("데이터 없음\n");
            return;
        }
        printer.print(member);
        System.out.println();
    }

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memDao = memberDao;
    }

    @Autowired
    @Qualifier("printer")
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }

}
