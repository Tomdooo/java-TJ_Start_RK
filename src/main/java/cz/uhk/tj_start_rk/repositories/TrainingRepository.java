package cz.uhk.tj_start_rk.repositories;

import cz.uhk.tj_start_rk.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training,Integer> {
}
