package cz.uhk.tj_start_rk.repositories;

import cz.uhk.tj_start_rk.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {}
