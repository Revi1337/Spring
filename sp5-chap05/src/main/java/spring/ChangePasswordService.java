package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO 1
// TODO 자동주입과 함께 하는 추가 기능이 컴포넌트 스캔. 컴포넌트 스캔은 스프링이 직접 클래스를 검색해서 빈으로 등록해주는 기능임.
// TODO 설정 클래스에 빈으로 등록하지 않아도 원하는 클래스를 빈으로 등록할 수 있으므로 컴포넌트 스캔 기능을 사용하면 설정 코드가 크게 줄어드는 장점이 있음.
// TODO 스프링이 검색해서 Bean 으로 등록할 수 있으면 클래스에 @Component 애노테이션을 붙여야함.
// TODO @Component 애노테이션은 해당 클래스를 스캔 대상으로 표시하는 것임.

@Component
public class ChangePasswordService {
    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();

        member.changePassword(oldPwd, newPwd);

        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}
