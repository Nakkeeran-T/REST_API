package com.example.demo.controller;

import com.example.demo.entity.Organizer;
import com.example.demo.service.OrganizerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    @Autowired
    OrganizerService organizerService;


    @GetMapping
    public List<Organizer> getAllOrganizers(){
        return organizerService.getOrganizers();
    }
    
    @PostMapping
    public Object addOrganizers(@RequestBody Organizer organizer) {
        return organizerService.addOrganizers(organizer);
    }

    @GetMapping("/paginate")
    public Page<Organizer> getOrganizerPage(@RequestParam int page, @RequestParam int size) {
        return organizerService.getOrganizerPage(page, size);
    }

    @GetMapping("/{id}")
    public Object getOrganizerById(@PathVariable Long id) {
        return organizerService.getOrganizerById(id);
    }

    @PutMapping("/{id}")
    public Object updateOrganizer(@PathVariable Long id, @RequestBody Organizer organizer) {
        return organizerService.updateOrganizer(id, organizer);
    }


    @DeleteMapping("/{id}")
    public String deleteOrganizer(@PathVariable Long id){
        return organizerService.deleteOrganizer(id);
    }
}