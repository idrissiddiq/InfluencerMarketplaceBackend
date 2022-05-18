package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.User;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import com.InfluencerMarketpalce.serverside.repository.InfluencerRepository;
import com.InfluencerMarketpalce.serverside.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sendy
 */
@Service
public class UserService extends ResponseStatus {
    
    private UserRepository userRepository;
    private InfluencerRepository employeeRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        User data = userRepository.findById(id).orElseThrow(this::dataNotFound);
        return data;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User delete(Long id) {
        User data = userRepository.findById(id).orElseThrow(this::dataNotFound);
        userRepository.deleteById(id);
        return data;
    }

    public User update(User user, Long id) {
        Optional<User> temp = userRepository.findById(id);
        if (!temp.isPresent()) {
            throw dataNotFound();
        }
        return userRepository.save(user);
    }
    
    
}
