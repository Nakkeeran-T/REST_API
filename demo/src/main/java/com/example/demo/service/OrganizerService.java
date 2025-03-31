package com.example.demo.service;

import com.example.demo.entity.Organizer;
import com.example.demo.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;



    public Organizer addOrganizers(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    public List<Organizer> getOrganizers() {
        return organizerRepository.findAll();
    }

    public Object getOrganizerById(Long id) {
        return organizerRepository.findById(id);
    }

    public String deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
        return "Organizer with " + id + " deleted successfully";
    }

    public Page<Organizer> getOrganizerPage(int page, int size){
        PageRequest pageable = PageRequest.of(page, size);
        return organizerRepository.findAll(pageable);
    }


    public Organizer creatOrganizer(Organizer organizer) {
        // Prevent Hibernate from persisting transient instances
        organizer.getEvents().forEach(event -> event.setOrganizer(organizer));
        return organizerRepository.save(organizer);
    }

    public Object updateOrganizer(Long id, Organizer organizer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrganizer'");
    }
}
