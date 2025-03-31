package com.example.demo.service;

import com.example.demo.entity.Venue;
import com.example.demo.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;


    public Venue addVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public List<Venue> getVenues() {
        return venueRepository.findAll();
    }

   public Object getById(Long id){
        return venueRepository.findById(id);
   }

    public Venue updateVenue(Long id, Venue venueDetails) {
        return venueRepository.findById(id)
                .map(venue -> {
                    venue.setName(venueDetails.getName());
                    venue.setLocation(venueDetails.getLocation());
                    venue.setCapacity(venueDetails.getCapacity());
                    return venueRepository.save(venue);
                })
                .orElseThrow(() -> new RuntimeException("Venue not found"));
    }

    public String deleteVenue(Long id){
        if(venueRepository.existsById(id)){
            venueRepository.deleteById(id);
            return "Venue with " + id + " deleted successfully";
        }
        else{
            return "Venue not found";
        }
    }
}
