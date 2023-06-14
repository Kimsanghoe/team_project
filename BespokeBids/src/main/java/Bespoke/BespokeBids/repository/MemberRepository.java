package Bespoke.BespokeBids.repository;


import Bespoke.BespokeBids.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsByUserId(String userId);
    boolean existsByUserIdAndPassword(String userId, String password);

    Optional<Member> findByUserId(String userId);


}
