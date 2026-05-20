package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookViewController {

    private final BookService bookService;

    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }
}