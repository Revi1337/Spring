public class MemberRegisterServicePRac {
//
//    private MemberDao memberDao = new MemberDao();
//
//    public void regist(RegisterRequest req) {
//        Member member = memberDao.selectByEmail(req.getEmail());
//
//        if(member != null) {
//             throw new DuplicateMemberException("dup email" + req.getEmail());
//        }
//
//        Member newMember = new Member(
//                    req.getEmail(),
//                    req.getPassword(),
//                    req.getName(),
//                    LocalDateTime.now());

        // TODO: 1
        // TODO: 해당 클래스의 이름인 MemberRegisterService 가 memberDao 클래스의 insert 메서드를 참조하고 있음. (이렇게 하나의 클래스가 다른 클래스의 메서드를 실행할 때 이를 "의존"한다고 표현함.) (여기서는 MemberRegisterService 는 MemberDao 에 의존 한다고 표현할 수 있음.)
        // TODO: 의존은 변경에 의해 영향을 받는 관계를 의미함. 예를 들어, memberDao 클래스의 insert() 메서드를 insertMember() 메서드로 변경하면 이 메서드를 사용하는 MemberRegisterService 클래스의 소스코드도 변하게되.
        // TODO: 이렇게 변경에 따른 영향이 전파되는 관계를 '의존' 한다고 표현함
//        memberDao.insert(newMember);

        // TODO: 이렇게 의존하는 대상이 있으면 그 대상을 구하는 방법이 필요한데, 가장 쉬운 방법은 의존하는 클래스에 의존 대상 객체를 직접 생성하는 것임.
        // TODO: MemberRegisterService 클래스에서 의존하는 MemberDao 객체를 직접 생성하기 떄문에 MemberRegisterService 객체를 생성하는 순간에 MemberDao 객체도 함꼐 생성되는 것임.
        // TODO: 이방법도 좋긴 하지만, 나중에 유지보수 관점에서 문제를 야기할 수 있음. 이렇게, 의존하는 객체를 직접 생성하는 방식 외에 의존 객체를 구하는 또 다른 방법이 있음.
        // TODO: 그 방법은 DI (Dependency Injection) 과 로케이터(Locator) 임. 하지만, 스프링과 관련된 것은 DI 임.
//        private MemberDao memberDao = new MemberDao();

        // TODO: DI 는 의존하는 객체(MemberDao) 를 직접생성하는 것 대신 의존 객체를 전달받는 방식을 사용함.

}
