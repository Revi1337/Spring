package spring;

import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

public class ChangePasswordService {

    private MemberDao memberDao;


    // @Transactional(rollbackFor = SQLException.class)    // 특정한 다른 오류에도 Rollback 하고 싶다면 rollbackFor 속성을 사용할 수 있음
    // value=<String> : 트랜잭션을 관리할 때 PlatformTransactionManager 빈의 이름을 지정.
    // propagation=<Propagation> : 트랜잭션 전파 타입을 지정 -> 디폴트는 Propagation.REQUIRED
    // isolation=<Isolation> : 트랜잭션 격리 레벨을 지정
    // timeout=<int> : 트랜잭션 제한 시간을 지정. 기본값은 -1 로 이 경우 데이터베이스의 타임아웃 시간을 사용함. (초단위)
    @Transactional
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
