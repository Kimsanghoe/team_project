package Bespoke.BespokeBids.repository;

import Bespoke.BespokeBids.domain.member.BiMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BiMemberRepository extends JpaRepository<BiMember, String> {
    boolean existsByUserId(String userId);
    boolean existsByUserIdAndPassword(String userId, String password);

    Optional<BiMember> findByUserId(String userId);

}
