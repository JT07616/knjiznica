package hr.fipu.knjiznica.service;

import hr.fipu.knjiznica.model.Member;
import hr.fipu.knjiznica.repository.LoanRepository;
import hr.fipu.knjiznica.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultMemberService implements MemberService {

    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;

    public DefaultMemberService(MemberRepository memberRepository, LoanRepository loanRepository) {
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Član nije pronađen."));
    }

    @Override
    @Transactional
    public Member create(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new RuntimeException("Član s tim emailom već postoji.");
        }

        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public Member update(Integer id, Member member) {
        Member existingMember = findById(id);

        if (memberRepository.existsByEmailAndIdNot(member.getEmail(), id)) {
            throw new RuntimeException("Član s tim emailom već postoji.");
        }

        existingMember.setFirstName(member.getFirstName());
        existingMember.setLastName(member.getLastName());
        existingMember.setEmail(member.getEmail());
        existingMember.setPhone(member.getPhone());

        return memberRepository.save(existingMember);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Member member = findById(id);

        if (!loanRepository.findByMemberId(id).isEmpty()) {
            throw new RuntimeException("Član se ne može obrisati jer ima evidentirane posudbe.");
        }

        memberRepository.delete(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Član s tim emailom nije pronađen."));
    }

    @Override
    public List<Member> searchByEmail(String email) {
        return memberRepository.findByEmailContainingIgnoreCase(email);
    }

    @Override
    public long count() {
        return memberRepository.count();
    }
}
