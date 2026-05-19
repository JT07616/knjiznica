package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.dto.MemberRequest;
import hr.fipu.knjiznica.model.Member;
import hr.fipu.knjiznica.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable Integer id) {
        return memberService.findById(id);
    }

    @PostMapping
    public Member create(@Valid @RequestBody MemberRequest request) {
        Member member = new Member(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone()
        );

        return memberService.create(member);
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable Integer id, @Valid @RequestBody MemberRequest request) {
        Member member = new Member(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone()
        );

        return memberService.update(id, member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        memberService.delete(id);
    }

    @GetMapping("/email")
    public Member findByEmail(@RequestParam String email) {
        return memberService.findByEmail(email);
    }
}