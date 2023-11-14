package Bespoke.BespokeBids.repository;

import Bespoke.BespokeBids.domain.member.BiMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BiMemberRepository extends JpaRepository<BiMember, String> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);


    Optional<BiMember> findByEmail(String email);


}
