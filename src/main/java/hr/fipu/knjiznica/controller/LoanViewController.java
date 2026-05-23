package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LoanViewController {

    private final LoanService loanService;

    public LoanViewController(LoanService loanService) {
        this.loanService = loanService;
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
}