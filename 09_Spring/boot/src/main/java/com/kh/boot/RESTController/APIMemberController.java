package com.kh.boot.RESTController;

import com.kh.boot.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody
// 모든 요청에 대한 응답을 뷰가 아닌 데이터로 직접 http 객체에 하겠다.
@RestController
@RequestMapping("/api/member")
public class APIMemberController {
    private final MemberService memberService;

    public APIMemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/id")
    public String checkMemberId(String checkId){
        int result = memberService.idCheck(checkId);

        if(result > 0){
            return "NNNNN";
        } else{
            return "NNNNY";
        }
    }
}
