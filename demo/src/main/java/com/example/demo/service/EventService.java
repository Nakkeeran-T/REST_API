package com.example.demo.service;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> getEvent() {
        return eventRepository.findAll();
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }
    
    public Object getEventById(Long id) {
        if(eventRepository.existsById(id))
        {
            return eventRepository.findById(id);
        }
        else{
            return "Event not found";
        }
    }

    public String deleteEvent(Long id)
    {
        if(eventRepository.existsById(id))
        {
            eventRepository.deleteById(id);
            return "Event with " + id + " deleted successfully";
        }
        else{
            return "Event not found";
        }
    }

    public Page<Event> getEventPage(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return eventRepository.findAll(pageable);
    }



    public Event updateEvent(Long id, Event eventDetails) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setEventName(eventDetails.getEventName());
                    event.setLocation(eventDetails.getLocation());
                    event.setDate(eventDetails.getDate());
                    event.setTime(eventDetails.getTime());
                    event.setAvailableSeats(eventDetails.getAvailableSeats());
                    event.setOrganizer(eventDetails.getOrganizer());
                    event.setVenue(eventDetails.getVenue());
                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }


}
