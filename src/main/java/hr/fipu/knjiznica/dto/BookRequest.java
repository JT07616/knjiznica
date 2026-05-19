package hr.fipu.knjiznica.dto;

import jakarta.validation.constraints.*;

public class BookRequest {

    @NotBlank(message = "Naslov knjige je obavezan.")
    @Size(max = 100, message = "Naslov moze imati najvise 100 znakova.")
    private String title;

    @NotBlank(message = "Autor knjige je obavezan.")
    @Size(max = 100, message = "Autor moze imati najvise 100 znakova.")
    private String author;

    @NotBlank(message = "Zanr knjige je obavezan.")
    @Size(max = 50, message = "Zanr moze imati najvise 50 znakova.")
    private String genre;

    @NotBlank(message = "ISBN je obavezan.")
    @Size(max = 20, message = "ISBN moze imati najvise 20 znakova.")
    @Pattern(regexp = "^[0-9-]+$", message = "ISBN smije sadrzavati samo brojeve i crtice.")
    private String isbn;

    @NotNull(message = "Godina izdavanja je obavezna.")
    @Min(value = 1, message = "Godina izdavanja mora biti veca od 0.")
    @Max(value = 2026, message = "Godina izdavanja ne moze biti u buducnosti.")
    private Integer publishedYear;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}