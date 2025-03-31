package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public Object addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "User with the email " + user.getEmail() + " already exists";
            
        }
        return userRepository.save(user);
    }


    public List<User> getUser(){
        return userRepository.findAll();
    }

    public Object getUserById(Long id) {
        if(userRepository.existsById(id))
        {
            return userRepository.findById(id);
        }
        else{
            return "User not found";
        }
    }


    public String deleteUser(Long id)
    {
        if(userRepository.existsById(id))
        {
            userRepository.deleteById(id);
            return "User with " + id + " deleted successfully";
        }
        else{
            return "User not found";
        }
    }

    public Object updateUser(Long id, User user)
    {
        if(userRepository.existsById(id))
        {
            User user1 = userRepository.findById(id).get();
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            return userRepository.save(user1);
        }
        else
        {
            return "User with " +id +"does not exist";
        }
    }

    
    public Page<User> getUserPage(int page, int size)
    {
        PageRequest pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
        // return userRepository.findAll(PageRequest.of(page, size)).getContent(); 
    }

    public List<User> sortbyname(String field)
    {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


   public Long countUsersByEmailDomain(String domain) {
        return userRepository.countUsersByEmailDomain(domain);
    }

}