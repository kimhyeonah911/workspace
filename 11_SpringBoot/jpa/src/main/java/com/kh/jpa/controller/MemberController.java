package com.kh.jpa.controller;

import com.kh.jpa.service.MemberService;
import com.kh.jpa.dto.MemberDto;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //회원 전체 조회 API
    @GetMapping
    public ResponseEntity<List<MemberDto.Response>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMember());
    }

    //회원 조회 API
    @GetMapping("/{userId}")
    public ResponseEntity<MemberDto.Response> getMember(@PathVariable String userId) {
        return ResponseEntity.ok(memberService.findMember(userId));
    }

    //이름으로 회원 검색 API
    @GetMapping("/search/name")
    public ResponseEntity<List<MemberDto.Response>> searchMemberByName(@RequestParam String name) {
        return ResponseEntity.ok(memberService.findByName(name));
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
