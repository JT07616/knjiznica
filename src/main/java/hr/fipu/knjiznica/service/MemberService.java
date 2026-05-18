package hr.fipu.knjiznica.service;

import hr.fipu.knjiznica.model.Member;

import java.util.List;

public interface MemberService {

    List<Member> findAll();

    Member findById(Integer id);

    Member create(Member member);

    Member update(Integer id, Member member);

    void delete(Integer id);

    Member findByEmail(String email);
}