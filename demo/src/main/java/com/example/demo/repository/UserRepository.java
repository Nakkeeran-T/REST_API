package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    //To count the domain of the email
    @Query("SELECT COUNT(u) FROM User u WHERE u.email LIKE CONCAT('%@', :domain)")
    Long countUsersByEmailDomain(@Param("domain") String domain);
    
}
