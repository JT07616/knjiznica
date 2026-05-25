package hr.fipu.knjiznica.service;

import hr.fipu.knjiznica.model.Member;
import hr.fipu.knjiznica.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultMemberService implements MemberService {

    private final MemberRepository memberRepository;

    public DefaultMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clan nije pronadjen."));
    }

    @Override
    @Transactional
    public Member create(Member member) {
        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public Member update(Integer id, Member member) {
        Member existingMember = findById(id);

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
        memberRepository.delete(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Clan s tim emailom nije pronadjen."));
    }

    @Override
    public long count() {
        return memberRepository.count();
    }
}
