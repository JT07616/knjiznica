package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.dto.LoanRequest;
import hr.fipu.knjiznica.service.BookService;
import hr.fipu.knjiznica.service.LoanService;
import hr.fipu.knjiznica.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoanViewController {

    private final LoanService loanService;
    private final BookService bookService;
    private final MemberService memberService;

    public LoanViewController(LoanService loanService, BookService bookService, MemberService memberService) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping("/loans")
    public String list(Model model) {
        model.addAttribute("loans", loanService.findAll());
        return "loans/list";
    }

    @GetMapping("/loans/{id}")
    public String details(@PathVariable Integer id, Model model) {
        model.addAttribute("loan", loanService.findById(id));
        return "loans/details";
    }

    @GetMapping("/loans/new")
    public String showCreateForm(Model model) {
        model.addAttribute("loanForm", new LoanRequest());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("members", memberService.findAll());
        return "loans/form";
    }

    @PostMapping("/loans")
    public String create(
            @Valid @ModelAttribute("loanForm") LoanRequest form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("books", bookService.findAll());
            model.addAttribute("members", memberService.findAll());
            return "loans/form";
        }

        loanService.create(form.getBookId(), form.getMemberId());
        redirectAttributes.addFlashAttribute("message", "Posudba je uspjesno dodana.");

        return "redirect:/loans";
    }
}