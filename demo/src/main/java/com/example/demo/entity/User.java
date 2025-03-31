package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> events;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        if(isValidPattern(name, NAME_REGEX)){
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Invalid name");
        }
    }
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        private static final String NAME_REGEX = "^[A-Z][a-z]+(?:[\\s'-][A-Z]?[a-z]*)*$";


    
    public String getEmail() {
        return email;
    }
    
    
    public void setEmail(String email) {
        if(isValidPattern(email, EMAIL_REGEX)){
            this.email = email;
        }else{
            throw new IllegalArgumentException("Invalid email");
        }
    }
    

    private boolean isValidPattern(String input, String regex){
        return Pattern.compile(regex).matcher(input).matches(); 
    }


    public List<Event> getEvents() {
        return events;
    }
    
    public void setEvents(List<Event> events) {
        this.events = events;
    }
}