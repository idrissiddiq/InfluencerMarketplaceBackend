/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.User;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Sendy
 */
@RestController
@RequestMapping("/api/user")
//@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseListData<User> findAll() {
        return new ResponseListData(userService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseData<User> findById(@PathVariable Long id) {
        return new ResponseData(userService.findById(id));
    }

    @PostMapping
    public ResponseData<User> create(@RequestBody User user) {
        return new ResponseData(userService.create(user));
    }
    
    @DeleteMapping("/{id}")
    public ResponseData<User> delete(@PathVariable Long id) {
        return new ResponseData(userService.delete(id));
    }
    
    @PutMapping("/{id}")
    public ResponseData<User> update(@RequestBody User user, @PathVariable Long id) {
        return new ResponseData(userService.update(user,id));
    }
}
