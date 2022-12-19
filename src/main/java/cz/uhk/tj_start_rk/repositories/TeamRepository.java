package cz.uhk.tj_start_rk.repositories;

import cz.uhk.tj_start_rk.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Integer> {
}
