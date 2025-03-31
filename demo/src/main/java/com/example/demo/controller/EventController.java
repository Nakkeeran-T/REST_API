package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventService eventService;
    
    @Autowired
    EventRepository eventRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getEvent();
    }

    @PostMapping
    public Object addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @GetMapping("/{id}")
    public Object getEventById(@PathVariable Long id)
    {
        return eventService.getEventById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable Long id)
    {
        return eventService.deleteEvent(id);
    }


    @GetMapping("paginate")
    public Page<Event> getEventPage(@RequestParam int page, @RequestParam int size)
    {
        return eventService.getEventPage(page, size);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        return eventRepository.findById(id)
        .map(event -> {
            event.setEventName(eventDetails.getEventName());
            event.setLocation(eventDetails.getLocation());
                    event.setDate(eventDetails.getDate());
                    event.setTime(eventDetails.getTime());
                    event.setAvailableSeats(eventDetails.getAvailableSeats());
                    event.setOrganizer(eventDetails.getOrganizer());
                    event.setVenue(eventDetails.getVenue());
                    Event updatedEvent = eventRepository.save(event);
                    return ResponseEntity.ok(updatedEvent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
}
