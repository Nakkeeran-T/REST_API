package com.example.demo.controller;

import com.example.demo.entity.Venue;
import com.example.demo.repository.VenueRepository;
import com.example.demo.service.VenueService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
public class VenueController {


    @Autowired
    VenueService venueService;
    VenueRepository venueRepository;

    @PostMapping
    public Object addVenue(@RequestBody Venue venue){
        return venueService.addVenue(venue);
    }

    @GetMapping
    public List<Venue> getVenues(){
        return venueService.getVenues();
    }

    @GetMapping("/{id}")
   public Object getById(@PathVariable long id){
    return venueService.getById(id);
   }

   @DeleteMapping("/{id}")
   public String deleteVenue(@PathVariable Long id) {
       return venueService.deleteVenue(id);
   }


    @PutMapping("/{id}")
    public ResponseEntity<Venue> updateVenue(@PathVariable Long id, @RequestBody Venue venueDetails) {
        return venueRepository.findById(id)
                .map(venue -> {
                    venue.setName(venueDetails.getName());
                    venue.setLocation(venueDetails.getLocation());
                    venue.setCapacity(venueDetails.getCapacity());
                    Venue updatedVenue = venueRepository.save(venue);
                    return ResponseEntity.ok(updatedVenue);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
