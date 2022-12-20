package cz.uhk.tj_start_rk.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import cz.uhk.tj_start_rk.model.Event;
import cz.uhk.tj_start_rk.model.json_view.View;
import cz.uhk.tj_start_rk.repositories.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    private final EventRepository eventRepository;

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

    @JsonView(View.AllEvent.class)
    @PostMapping("/events")
    public Event addEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @JsonView(View.AllEvent.class)
    @PutMapping("/events")
    public Event updateEvent(@RequestBody Event event) {
        Event update = eventRepository.getReferenceById(event.getId());

        update.update(event);

        return eventRepository.save(event);
    }

    @JsonView(View.AllEvent.class)
    @DeleteMapping("/events/{id}")
    public void deleteEventById(@PathVariable int id){
        eventRepository.deleteById(id);
    }
}
