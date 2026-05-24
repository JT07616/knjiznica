package hr.fipu.knjiznica.service;

import hr.fipu.knjiznica.model.Book;
import hr.fipu.knjiznica.model.Loan;
import hr.fipu.knjiznica.model.Member;
import hr.fipu.knjiznica.repository.BookRepository;
import hr.fipu.knjiznica.repository.LoanRepository;
import hr.fipu.knjiznica.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultLoanService implements LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public DefaultLoanService(LoanRepository loanRepository, BookRepository bookRepository, MemberRepository memberRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public Loan findById(Integer id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Posudba nije pronadjena."));
    }

    @Override
    @Transactional
    public Loan create(Integer bookId, Integer memberId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Knjiga nije pronadjena."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Clan nije pronadjen."));

        if (!book.isAvailable()) {
            throw new RuntimeException("Knjiga trenutno nije dostupna za posudbu.");
        }

        book.setAvailable(false);

        Loan loan = new Loan(book, member);
        return loanRepository.save(loan);
    }

    @Override
    @Transactional
    public Loan returnBook(Integer loanId) {
        Loan loan = findById(loanId);

        if ("RETURNED".equals(loan.getStatus())) {
            throw new RuntimeException("Knjiga je vec vracena.");
        }

        loan.returnLoan();

        return loanRepository.save(loan);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Loan loan = findById(id);

        if ("ACTIVE".equals(loan.getStatus())) {
            loan.getBook().setAvailable(true);
        }

        loanRepository.delete(loan);
    }

    @Override
    @Transactional
    public Loan updateMember(Integer loanId, Integer memberId) {
        Loan loan = findById(loanId);

        if ("RETURNED".equals(loan.getStatus())) {
            throw new RuntimeException("Vracena posudba se ne moze uredjivati.");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Clan nije pronadjen."));

        loan.setMember(member);

        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> findByStatus(String status) {
        return loanRepository.findByStatus(status);
    }

    @Override
    public List<Loan> findByBookId(Integer bookId) {
        return loanRepository.findByBookId(bookId);
    }

    @Override
    public List<Loan> findByMemberId(Integer memberId) {
        return loanRepository.findByMemberId(memberId);
    }
}