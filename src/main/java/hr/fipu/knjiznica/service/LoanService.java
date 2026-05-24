package hr.fipu.knjiznica.service;

import hr.fipu.knjiznica.model.Loan;

import java.util.List;

public interface LoanService {

    List<Loan> findAll();

    Loan findById(Integer id);

    Loan create(Integer bookId, Integer memberId);

    Loan returnBook(Integer loanId);

    Loan updateMember(Integer loanId, Integer memberId);

    void delete(Integer id);

    List<Loan> findByStatus(String status);

    List<Loan> findByBookId(Integer bookId);

    List<Loan> findByMemberId(Integer memberId);
}