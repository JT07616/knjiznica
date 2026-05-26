package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.model.Book;
import hr.fipu.knjiznica.model.Loan;
import hr.fipu.knjiznica.service.BookService;
import hr.fipu.knjiznica.service.LoanService;
import hr.fipu.knjiznica.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final BookService bookService;
    private final MemberService memberService;
    private final LoanService loanService;

    public HomeController(BookService bookService, MemberService memberService, LoanService loanService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.loanService = loanService;
    }

    @ModelAttribute("activePage")
    public String activePage() {
        return "home";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Book> borrowedBooks = loanService.findAll()
                .stream()
                .filter(loan -> Loan.STATUS_ACTIVE.equals(loan.getStatus()))
                .map(Loan::getBook)
                .toList();

        model.addAttribute("bookCount", bookService.count());
        model.addAttribute("memberCount", memberService.count());
        model.addAttribute("loanCount", loanService.count());
        model.addAttribute("activeLoanCount", loanService.countActive());
        model.addAttribute("borrowedBooks", borrowedBooks);
        model.addAttribute("hasBorrowedBooks", !borrowedBooks.isEmpty());

        return "index";
    }
}
