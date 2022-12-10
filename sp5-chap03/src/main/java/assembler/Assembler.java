package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {

    private MemberDao memberDao;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;

    // TODO: 1
    // MemberRegisterService 객체와 ChangePasswordService 객체에 대한 의존을 주입.
    // MemberRegisterService 는 생성자를 통해 MemberDao 객체를 주입받고, ChangePasswordService 는 세터를 통해 주입받음.
    public Assembler(){
        memberDao = new MemberDao();
        regSvc = new MemberRegisterService(memberDao);
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService() {
        return regSvc;
    }

    public ChangePasswordService getChangePasswordService() {
        return pwdSvc;
    }

}
