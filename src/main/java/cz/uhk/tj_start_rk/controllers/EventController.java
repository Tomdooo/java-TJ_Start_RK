package cz.uhk.tj_start_rk.controllers;

import cz.uhk.tj_start_rk.model.Event;
import cz.uhk.tj_start_rk.model.Team;
import cz.uhk.tj_start_rk.repositories.EventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
    @GetMapping("/events/{id}")
    public Optional<Event> getTeams(@PathVariable int id){
        return eventRepository.findById(id);
    }
}
