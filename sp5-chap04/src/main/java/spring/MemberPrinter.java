package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

public class MemberPrinter {

    private DateTimeFormatter dateTimeFormatter;

    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }

    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf(
                    "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                    member.getId(), member.getEmail(),
                    member.getName(), member.getRegisterDateTime());
        } else {
            System.out.printf(
                    "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                    member.getId(), member.getEmail(),
                    member.getName(),
                    dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

    // TODO: 해당 세터 메서드가 필요료하는 DateTimeFormatter 타입의 Bean 이 없으면 익셉션이 발생함. 따라서, 값이나 Null 을 넣어주면 됨.
    // TODO: 하지만 자동 주입할 대상이 필수가 아니면 @Autowired 어노테이션의 required 속성에 false 를 하면됨. (매칭되는 Bean 이 없어도 익셉션이 발생하지 않으며 자동 주입을 수행하지 않는 것임.)
    // TODO: 스프링 2.5부터 required false 말고 Optional 을 사용해도 됨. (일치하는 Bean 이 존재하면 해당 빈을 값으로 갖는 Optional 을 진자로 전달하고,  없으면 null 이 인자로 전달되고 익셉션이 발생하지 않는 것임.)
    // TODO: 세터메서드의 매개변수에 @Nullable 을 붙여주면, 스프링 컨테이너는 세터 메서드를 호출할 떄 자동 주입할 빈이 존재하면 해당 빈을 인자로 전달하고, 존재하지 않으면 인자로 null 을 전달함
    // TODO: @Autowired(required=false) 와 @Nullable 의 차이는 @Nullable 은 자동 주입할 빈이 존재하지 않아도 세터 메서드가 호출된다는 것임. @Autowired 는 Bean 이 존재하지 않으면 세터 메서드를 호출하지 않음.
    @Autowired(required = false)
    public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

//	@Autowired
//	public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
//		if (formatterOpt.isPresent()) {
//			this.dateTimeFormatter = formatterOpt.get();
//		} else {
//			this.dateTimeFormatter = null;
//		}
//	}

//	@Autowired
//	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
}
