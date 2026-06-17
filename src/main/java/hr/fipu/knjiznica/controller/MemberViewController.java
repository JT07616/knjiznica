package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.dto.MemberRequest;
import hr.fipu.knjiznica.model.Member;
import hr.fipu.knjiznica.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberViewController {

    private final MemberService memberService;

    public MemberViewController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ModelAttribute("activePage")
    public String activePage() {
        return "members";
    }

    @GetMapping("/members")
    public String list(@RequestParam(required = false) String email, Model model) {
        if (email == null || email.isBlank()) {
            model.addAttribute("members", memberService.findAll());
        } else {
            model.addAttribute("members", memberService.searchByEmail(email));
        }

        model.addAttribute("email", email);
        return "members/list";
    }

    @GetMapping("/members/{id}")
    public String details(@PathVariable Integer id, Model model) {
        model.addAttribute("member", memberService.findById(id));
        return "members/details";
    }

    @GetMapping("/members/new")
    public String showCreateForm(Model model) {
        model.addAttribute("memberForm", new MemberRequest());
        model.addAttribute("formTitle", "Dodaj člana");
        model.addAttribute("formAction", "/members");
        return "members/form";
    }

    @PostMapping("/members")
    public String create(
            @Valid @ModelAttribute("memberForm") MemberRequest form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("formTitle", "Dodaj člana");
            model.addAttribute("formAction", "/members");
            return "members/form";
        }

        Member member = new Member(
                form.getFirstName(),
                form.getLastName(),
                form.getEmail(),
                form.getPhone()
        );

        try {
            memberService.create(member);
        } catch (RuntimeException e) {
            bindingResult.rejectValue("email", "duplicate", e.getMessage());
            model.addAttribute("formTitle", "Dodaj člana");
            model.addAttribute("formAction", "/members");
            return "members/form";
        }
        redirectAttributes.addFlashAttribute("message", "Član je uspješno dodan.");

        return "redirect:/members";
    }

    @GetMapping("/members/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Member member = memberService.findById(id);

        MemberRequest form = new MemberRequest();
        form.setFirstName(member.getFirstName());
        form.setLastName(member.getLastName());
        form.setEmail(member.getEmail());
        form.setPhone(member.getPhone());

        model.addAttribute("memberForm", form);
        model.addAttribute("memberId", id);
        model.addAttribute("formTitle", "Uredi člana");
        model.addAttribute("formAction", "/members/" + id);

        return "members/form";
    }

    @PostMapping("/members/{id}")
    public String update(
            @PathVariable Integer id,
            @Valid @ModelAttribute("memberForm") MemberRequest form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("memberId", id);
            model.addAttribute("formTitle", "Uredi člana");
            model.addAttribute("formAction", "/members/" + id);
            return "members/form";
        }

        Member member = new Member(
                form.getFirstName(),
                form.getLastName(),
                form.getEmail(),
                form.getPhone()
        );

        try {
            memberService.update(id, member);
        } catch (RuntimeException e) {
            bindingResult.rejectValue("email", "duplicate", e.getMessage());
            model.addAttribute("memberId", id);
            model.addAttribute("formTitle", "Uredi člana");
            model.addAttribute("formAction", "/members/" + id);
            return "members/form";
        }
        redirectAttributes.addFlashAttribute("message", "Član je uspješno uređen.");

        return "redirect:/members";
    }

    @GetMapping("/members/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Integer id, Model model) {
        model.addAttribute("member", memberService.findById(id));
        return "members/delete";
    }

    @PostMapping("/members/{id}/delete")
    public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            memberService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Član je uspješno obrisan.");
            return "redirect:/members";
        } catch (RuntimeException e) {
            model.addAttribute("member", memberService.findById(id));
            model.addAttribute("errorMessage", e.getMessage());
            return "members/delete";
        }
    }


}
