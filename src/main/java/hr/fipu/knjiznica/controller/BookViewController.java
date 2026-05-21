package hr.fipu.knjiznica.controller;

import hr.fipu.knjiznica.dto.BookRequest;
import hr.fipu.knjiznica.model.Book;
import hr.fipu.knjiznica.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookViewController {

    private final BookService bookService;

    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String list(@RequestParam(required = false) String title, Model model) {
        if (title == null || title.isBlank()) {
            model.addAttribute("books", bookService.findAll());
        } else {
            model.addAttribute("books", bookService.searchByTitle(title));
        }

        model.addAttribute("title", title);
        return "books/list";
    }

    @GetMapping("/books/{id}")
    public String details(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "books/details";
    }

    @GetMapping("/books/new")
    public String showCreateForm(Model model) {
        model.addAttribute("bookForm", new BookRequest());
        model.addAttribute("formTitle", "Dodaj knjigu");
        model.addAttribute("formAction", "/books");
        return "books/form";
    }

    @PostMapping("/books")
    public String create(
            @Valid @ModelAttribute("bookForm") BookRequest form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("formTitle", "Dodaj knjigu");
            model.addAttribute("formAction", "/books");
            return "books/form";
        }

        Book book = new Book(
                form.getTitle(),
                form.getAuthor(),
                form.getGenre(),
                form.getIsbn(),
                form.getPublishedYear()
        );

        bookService.create(book);
        redirectAttributes.addFlashAttribute("message", "Knjiga je uspjesno dodana.");

        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Book book = bookService.findById(id);

        BookRequest form = new BookRequest();
        form.setTitle(book.getTitle());
        form.setAuthor(book.getAuthor());
        form.setGenre(book.getGenre());
        form.setIsbn(book.getIsbn());
        form.setPublishedYear(book.getPublishedYear());

        model.addAttribute("bookForm", form);
        model.addAttribute("bookId", id);
        model.addAttribute("formTitle", "Uredi knjigu");
        model.addAttribute("formAction", "/books/" + id);

        return "books/form";
    }

    @PostMapping("/books/{id}")
    public String update(
            @PathVariable Integer id,
            @Valid @ModelAttribute("bookForm") BookRequest form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("bookId", id);
            model.addAttribute("formTitle", "Uredi knjigu");
            model.addAttribute("formAction", "/books/" + id);
            return "books/form";
        }

        Book book = new Book(
                form.getTitle(),
                form.getAuthor(),
                form.getGenre(),
                form.getIsbn(),
                form.getPublishedYear()
        );

        bookService.update(id, book);
        redirectAttributes.addFlashAttribute("message", "Knjiga je uspjesno uredjena.");

        return "redirect:/books";
    }

    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        bookService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Knjiga je uspjesno obrisana.");

        return "redirect:/books";
    }


}