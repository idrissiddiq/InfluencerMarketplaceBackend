/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.response.*;
import com.InfluencerMarketpalce.serverside.repository.*;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;

import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Idris Siddiq
 */
@Service
public class RegistService extends ResponseStatus {
    private InfluencerRepository influencerRepository;
    private BrandRepository brandRepository;
    private UserRepository userRepository;
    private UserBrandRepository userBrandRepository;
    private JobRepository jobRepository;
    private PasswordEncoder encoder;
    private RoleRepository roleRepository;
    private EmailService emailService;
    
    
    @Autowired
    public RegistService(InfluencerRepository influencerRepository, BrandRepository brandRepository, UserRepository userRepository, UserBrandRepository userBrandRepository, JobRepository jobRepository, PasswordEncoder encoder, RoleRepository roleRepository, EmailService emailService) {
        this.influencerRepository = influencerRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.userBrandRepository = userBrandRepository;
        this.jobRepository = jobRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
    }
    
    public User setPassword(String pass, String username){
        User user = userRepository.findByUsername(username);
        user.setPassword(encoder.encode(pass));
        return userRepository.save(user);
    }

    public ResponseMessage<RegisterBrandRequest> registBrand(RegisterBrandRequest request) {
        long temp = brandRepository.findEmail(request.getEmail());
        if (temp >= 1) {
            return new ResponseMessage<>("Error -  Email Already Registered", new RegisterBrandRequest(request.getId(), request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), request.getPassword()));
        }
        long tempUser = userBrandRepository.countByUsername(request.getUsername());
        if (tempUser >= 1) {
            return new ResponseMessage<>("Error -  Username Already Registered", new RegisterBrandRequest(request.getId(), request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), request.getPassword()));
        }
        Brand brand = new Brand();
        brand.setId(request.getId());
        brand.setFullname(request.getFullname());
        brand.setEmail(request.getEmail());
        Job job = jobRepository.findByIdJob(request.getJobId());
        brand.setBrandjob(job);

        UserBrand userBrand = new UserBrand();
        userBrand.setBrand(brand);
        userBrand.setId(request.getId());
        userBrand.setUsername(request.getUsername());
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        System.out.println("Hasil Generate : " + generatedString);
        userBrand.setPassword(encoder.encode(generatedString));
        Set<Role> temp_role = roleRepository.findByName("Influencer");
        userBrand.setRoles(temp_role);
//        if("P".equals(request.getJobId())){
//            System.out.println("Masuk Peserta");
//
//        }
//        else if("T".equals(request.getJobId())){
//            Set<Role> temp_role = roleRepository.findByName("Trainer");
//            user.setRoles(temp_role);
//        }
        brand.setUser(userBrand);
        //emailService.sendEmail(request.getEmail(), request.getUsername(), generatedString);
        brandRepository.save(brand);
        userBrandRepository.save(userBrand);
        return new ResponseMessage<>("Brand Registered", new RegisterBrandRequest(request.getId(), request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), generatedString));
    }
    
    public ResponseMessage<RegisterInfluencerRequest> regist(RegisterInfluencerRequest request) {
        long temp = influencerRepository.findEmail(request.getEmail());
        if (temp >= 1) {
            return new ResponseMessage<>("Error -  Email Already Registered", new RegisterInfluencerRequest(request.getId(), request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), request.getPassword()));
        }
        long tempUser = userRepository.countByUsername(request.getUsername());
        if (tempUser >= 1) {
            return new ResponseMessage<>("Error -  Username Already Registered", new RegisterInfluencerRequest(request.getId(), request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), request.getPassword()));
        }
        Influencer influencer = new Influencer();
        influencer.setId(request.getId());
        influencer.setFullname(request.getFullname());
        influencer.setEmail(request.getEmail());
        Job job = jobRepository.findByIdJob(request.getJobId());
        influencer.setJob(job);

        User user = new User();
        user.setInfluencer(influencer);
        user.setId(request.getId());
        user.setUsername(request.getUsername());
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        System.out.println("Hasil Generate : " + generatedString);
        user.setPassword(encoder.encode(generatedString));
        Set<Role> temp_role = roleRepository.findByName("Influencer");
        user.setRoles(temp_role);
//        if("P".equals(request.getJobId())){
//            System.out.println("Masuk Peserta");
//
//        }
//        else if("T".equals(request.getJobId())){
//            Set<Role> temp_role = roleRepository.findByName("Trainer");
//            user.setRoles(temp_role);
//        }
        influencer.setUser(user);
        //emailService.sendEmail(request.getEmail(), request.getUsername(), generatedString);
        influencerRepository.save(influencer);
        userRepository.save(user);
        return new ResponseMessage<>("Influencer Registered", new RegisterInfluencerRequest(request.getId(), request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), generatedString));
    }
    
    public ForgotPasswordRequest forgot(ForgotPasswordRequest request){
        Long temp = influencerRepository.findIdByEmail(request.getEmail());
        if (temp == null) {
            throw dataNotFound();
        }
        User temp_user = userRepository.findUsernameById(temp);
        System.out.println(temp_user);
        User user = new User();
        user.setId(temp);
        user.setUsername(temp_user.getUsername());
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        System.out.println("Hasil Generate : " + generatedString);
        user.setPassword(encoder.encode(generatedString));
        userRepository.save(user);
        emailService.forgotEmail(request.getEmail(), temp_user.getUsername(), generatedString);
        return new ForgotPasswordRequest(request.getEmail());
    }
    
    public ResponseMessage<RegisterInfluencerRequest> update(RegisterInfluencerRequest request, Long id) {
        long temp_count = influencerRepository.findEmail(request.getEmail());
        String temp_email = influencerRepository.findEmailById(id);
        long tempCount = userRepository.countByUsername(request.getUsername());
        String tempUser = userRepository.findUserById(id);
        Optional<Influencer> temp = influencerRepository.findById(id);
        if (!temp.isPresent()) {
            throw dataNotFound();
        }
        if (temp_count >= 1 && request.getEmail() != temp_email){
            return new ResponseMessage<>("Error - Email Already Registered" , new RegisterInfluencerRequest(request.getId(),request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), request.getPassword()));
        }
        if (tempCount >= 1 && request.getUsername() != tempUser){
            return new ResponseMessage<>("Error - Username Already Registered" , new RegisterInfluencerRequest(request.getId(),request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), request.getPassword()));
        }
        Influencer influencer = new Influencer();
        influencer.setId(id);
        influencer.setFullname(request.getFullname());
        influencer.setEmail(request.getEmail());
        Job job = jobRepository.findByIdJob(request.getJobId());
        influencer.setJob(job);
        
        User user = new User();
        user.setInfluencer(influencer);
        user.setId(id);
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode("pass"));
        if("P".equals(request.getJobId())){
            System.out.println("Masuk Peserta");
            Set<Role> temp_role = roleRepository.findByName("Peserta");
            user.setRoles(temp_role);
        }
        else if("T".equals(request.getJobId())){
            Set<Role> temp_role = roleRepository.findByName("Trainer");
            user.setRoles(temp_role);
        }
        influencer.setUser(user);

        influencerRepository.save(influencer);
        userRepository.save(user);
        
        return new ResponseMessage<>("Employee Updated" , new RegisterInfluencerRequest(request.getId(),request.getFullname(), request.getEmail(), request.getJobId(), request.getUsername(), request.getPassword()));
    }
    
    public RegisterInfluencerResponse getById(Long id) {
        Optional<Influencer> temp = influencerRepository.findById(id);
        if (!temp.isPresent()) {
            throw dataNotFound();
        }
        
        Influencer influencer = new Influencer();
        influencer = influencerRepository.getById(id);
        User user = new User();
        user  = userRepository.getById(id);
        return new RegisterInfluencerResponse(influencer.getId(),influencer.getFullname(), influencer.getEmail(), influencer.getJob(), user.getUsername(), user.getPassword());
    }
}
