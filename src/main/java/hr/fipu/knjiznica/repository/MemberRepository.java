package hr.fipu.knjiznica.repository;

import hr.fipu.knjiznica.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(String email);

    List<Member> findByEmailContainingIgnoreCase(String email);
}
