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

    @ModelAttribute("activePage")
    public String activePage() {
        return "loans";
    }

    @GetMapping("/loans")
    public String list(@RequestParam(required = false) String status, Model model) {
        if (status == null || status.isBlank()) {
            model.addAttribute("loans", loanService.findAll());
        } else {
            model.addAttribute("loans", loanService.findByStatus(status));
        }

        model.addAttribute("status", status);
        return "loans/list";
    }

    @GetMapping("/loans/{id}")
    public String details(@PathVariable Integer id, Model model) {
        model.addAttribute("loan", loanService.findById(id));
        return "loans/details";
    }

    @GetMapping("/loans/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        var loan = loanService.findById(id);

        LoanRequest form = new LoanRequest();
        form.setBookId(loan.getBook().getId());
        form.setMemberId(loan.getMember().getId());

        model.addAttribute("loan", loan);
        model.addAttribute("loanForm", form);
        model.addAttribute("members", memberService.findAll());

        return "loans/edit";
    }

    @PostMapping("/loans/{id}")
    public String update(
            @PathVariable Integer id,
            @Valid @ModelAttribute("loanForm") LoanRequest form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("loan", loanService.findById(id));
            model.addAttribute("members", memberService.findAll());
            return "loans/edit";
        }

        loanService.updateMember(id, form.getMemberId());
        redirectAttributes.addFlashAttribute("message", "Posudba je uspješno uređena.");

        return "redirect:/loans";
    }

    @PostMapping("/loans/{id}/return")
    public String returnBook(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        loanService.returnBook(id);
        redirectAttributes.addFlashAttribute("message", "Knjiga je uspješno vraćena.");

        return "redirect:/loans";
    }

    @GetMapping("/loans/{id}/delete")
    public String showDeleteConfirmation(@PathVariable Integer id, Model model) {
        model.addAttribute("loan", loanService.findById(id));
        return "loans/delete";
    }

    @PostMapping("/loans/{id}/delete")
    public String delete(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            loanService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Posudba je uspješno obrisana.");
            return "redirect:/loans";
        } catch (RuntimeException e) {
            model.addAttribute("loan", loanService.findById(id));
            model.addAttribute("errorMessage", e.getMessage());
            return "loans/delete";
        }
    }



    @GetMapping("/loans/new")
    public String showCreateForm(Model model) {
        model.addAttribute("loanForm", new LoanRequest());
        model.addAttribute("books", bookService.findAvailable());
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
            model.addAttribute("books", bookService.findAvailable());
            model.addAttribute("members", memberService.findAll());
            return "loans/form";
        }

        loanService.create(form.getBookId(), form.getMemberId());
        redirectAttributes.addFlashAttribute("message", "Posudba je uspješno dodana.");

        return "redirect:/loans";
    }
}
