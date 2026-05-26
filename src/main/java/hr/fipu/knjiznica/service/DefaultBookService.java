package hr.fipu.knjiznica.service;

import hr.fipu.knjiznica.model.Book;
import hr.fipu.knjiznica.repository.BookRepository;
import hr.fipu.knjiznica.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public DefaultBookService(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Knjiga nije pronadjena."));
    }

    @Override
    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book update(Integer id, Book book) {
        Book existingBook = findById(id);

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setGenre(book.getGenre());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublishedYear(book.getPublishedYear());

        return bookRepository.save(existingBook);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Book book = findById(id);

        if (!loanRepository.findByBookId(id).isEmpty()) {
            throw new RuntimeException("Knjiga se ne može obrisati jer ima evidentirane posudbe.");
        }

        bookRepository.delete(book);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> findAvailable() {
        return bookRepository.findByAvailableTrue();
    }

    @Override
    public long count() {
        return bookRepository.count();
    }
}
