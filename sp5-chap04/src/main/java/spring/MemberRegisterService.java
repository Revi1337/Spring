package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class MemberRegisterService {

    @Autowired
    private MemberDao memberDao;

    // TODO: 기본 생성자를 추가한 이유는 AppCtx 설정 클래스에서 기본 생성자를 이용해서 객체를 추가하기 위함임.
    public MemberRegisterService() {
    }

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }
        Member newMember = new Member(
                req.getEmail(), req.getPassword(), req.getName(),
                LocalDateTime.now());
        memberDao.insert(newMember);
        return newMember.getId();
    }
}
