package cz.uhk.tj_start_rk.repositories;

import cz.uhk.tj_start_rk.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
