package hr.fipu.knjiznica.service;

import hr.fipu.knjiznica.model.Book;
import hr.fipu.knjiznica.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Knjiga nije pronađena."));
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
        existingBook.setAvailable(book.isAvailable());

        return bookRepository.save(existingBook);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }

    @Override
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}