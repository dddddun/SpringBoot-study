package woodangtang.woodangtangspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import woodangtang.woodangtangspring.service.MemberService;

/* 스프링 컨테이너가 스프링 참에 뜰 때, 스프링 컨테이너라는 스프링 통이 생기는데
거기에 @Controller라는 애노테이션이 있으면 MemberController 객체를 생성해 넣어두고
스프링이 관리한다. 이것을 스프링 컨테이너에서 스프링 빈이 관리된다 라고 한다. */

@Controller
public class MemberController {

    private final MemberService memberService;

    // 멤버컨트롤러는 스프링컨테이너가 뜰때 생성되고 그 때 생성자를 호출한다.
    // Autowired라고 되어있으면 스프링이 스프링 컨테이너에 있는 멤버 서비스와 연결해준다.
    @Autowired
    // 생성자.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
