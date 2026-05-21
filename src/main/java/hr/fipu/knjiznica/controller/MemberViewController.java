package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberViewController {

    private final MemberService memberService;

    public MemberViewController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "members/list";
    }

    @GetMapping("/members/{id}")
    public String details(@PathVariable Integer id, Model model) {
        model.addAttribute("member", memberService.findById(id));
        return "members/details";
    }
}