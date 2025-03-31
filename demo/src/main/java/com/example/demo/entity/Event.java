package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String eventName;

    private String location;
    private String date;
    private String time;
    private int availableSeats;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false) // Foreign key to Venue
    private Venue venue; // Relationship with Venue

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false) // Foreign key to Organizer
    private Organizer organizer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User
    private User user; // Relationship with User
}