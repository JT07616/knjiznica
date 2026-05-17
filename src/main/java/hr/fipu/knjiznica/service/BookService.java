package hr.fipu.knjiznica.service;

import hr.fipu.knjiznica.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(Integer id);

    Book create(Book book);

    Book update(Integer id, Book book);

    void delete(Integer id);

    List<Book> searchByTitle(String title);
}