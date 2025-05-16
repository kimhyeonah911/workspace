package com.kh.jpa.controller;

import com.kh.jpa.repository.MemberRepository;
import com.kh.jpa.service.MemberService;
import com.kh.jpa.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    //회원 등록 API
    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody MemberDto.Create createDto) {
        String userId = memberService.createMember(createDto);
        //return new ResponseEntity<String>(userId, HttpStatus.OK);
        return ResponseEntity.ok(userId);
    }

    //회원 조회 API
    @GetMapping("/{userId}")
    public ResponseEntity<MemberDto.Response> getMember(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.findMember(userId));
    }

    //회원 수정 API
    @PutMapping("/{userId}")
    public ResponseEntity<MemberDto.Response> updateMember(@PathVariable String userId, @RequestBody MemberDto.Update updateDto) {
        return ResponseEntity.ok(memberService.updateMember(userId, updateDto));
    }

    //회원 삭제 API
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String userId) {
        memberService.deleteMember(userId);
        return ResponseEntity.ok().build();
    }
}
