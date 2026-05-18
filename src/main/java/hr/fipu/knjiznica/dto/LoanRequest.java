package hr.fipu.knjiznica.dto;

import jakarta.validation.constraints.*;

public class LoanRequest {

    @NotNull(message = "ID knjige je obavezan.")
    private Integer bookId;

    @NotNull(message = "ID clana je obavezan.")
    private Integer memberId;

    public Integer getBookId() {
        return bookId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}