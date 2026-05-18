package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.model.Loan;
import hr.fipu.knjiznica.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> findAll() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public Loan findById(@PathVariable Integer id) {
        return loanService.findById(id);
    }

    @PostMapping
    public Loan create(@RequestParam Integer bookId, @RequestParam Integer memberId) {
        return loanService.create(bookId, memberId);
    }

    @PutMapping("/{id}/return")
    public Loan returnBook(@PathVariable Integer id) {
        return loanService.returnBook(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        loanService.delete(id);
    }

    @GetMapping("/status")
    public List<Loan> findByStatus(@RequestParam String status) {
        return loanService.findByStatus(status);
    }

    @GetMapping("/book/{bookId}")
    public List<Loan> findByBookId(@PathVariable Integer bookId) {
        return loanService.findByBookId(bookId);
    }

    @GetMapping("/member/{memberId}")
    public List<Loan> findByMemberId(@PathVariable Integer memberId) {
        return loanService.findByMemberId(memberId);
    }
}