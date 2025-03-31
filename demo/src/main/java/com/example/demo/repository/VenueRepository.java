package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
}
