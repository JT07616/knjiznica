package hr.fipu.knjiznica.repository;

import hr.fipu.knjiznica.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    List<Loan> findByStatus(String status);

    long countByStatus(String status);

    List<Loan> findByBookId(Integer bookId);

    List<Loan> findByMemberId(Integer memberId);
}
