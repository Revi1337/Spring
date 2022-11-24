package spring;

import org.springframework.beans.factory.annotation.Autowired;
import spring.Member;
import spring.MemberDao;
import spring.MemberPrinter;

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

    // TODO: DI 방식 2 인 세터 메서드방식은 규칙이 있음.
    // TODO: 1. 메서드 이름이 set 으로 시작. 2. set 뒤에 첫글자는 대문자로 시작
    // TODO: 3. 파라미터가 1개이어야 함. 리턴타입이 void 이어야 함.
    public void setMemberDao(MemberDao memberDao) {
        this.memDao = memberDao;
    }

    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }

}
