package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Event;
import cz.uhk.tj_start_rk.model.json_view.View;
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

    @JsonView(View.AllEvent.class)
    @GetMapping("/events")
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @JsonView(View.AllEvent.class)
    @GetMapping("/events/{id}")
    public Optional<Event> getTeams(@PathVariable int id){
        return eventRepository.findById(id);
    }
}
