/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.request.ForgotPasswordRequest;
import com.InfluencerMarketpalce.serverside.model.request.InfluencerChangePasswordRequest;
import com.InfluencerMarketpalce.serverside.model.request.RegisterBrandRequest;
import com.InfluencerMarketpalce.serverside.model.request.RegisterInfluencerRequest;
import com.InfluencerMarketpalce.serverside.model.response.*;
import com.InfluencerMarketpalce.serverside.repository.*;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;

import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private InfluenceTypeRepository influenceTypeRepository;
    
    
    @Autowired
    public RegistService(InfluencerRepository influencerRepository, BrandRepository brandRepository, UserRepository userRepository, UserBrandRepository userBrandRepository, JobRepository jobRepository, PasswordEncoder encoder, RoleRepository roleRepository, EmailService emailService, InfluenceTypeRepository influenceTypeRepository) {
        this.influencerRepository = influencerRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.userBrandRepository = userBrandRepository;
        this.jobRepository = jobRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.influenceTypeRepository = influenceTypeRepository;
    }
    
    public User setPassword(String pass, String username){
        User user = userRepository.findByUsername(username);
        user.setPassword(encoder.encode(pass));
        return userRepository.save(user);
    }

    public ResponseMessage<RegisterBrandRequest> registBrand(RegisterBrandRequest request) {
        long temp = brandRepository.findEmail(request.getEmail());
        if (temp >= 1) {
            return new ResponseMessage<>("Error -  Email Already Registered", new RegisterBrandRequest(request.getId(), request.getFullname(), request.getEmail(), request.getUsername()));
        }
        long tempUser = userBrandRepository.countByUsername(request.getUsername());
        if (tempUser >= 1) {
            return new ResponseMessage<>("Error -  Username Already Registered", new RegisterBrandRequest(request.getId(), request.getFullname(), request.getEmail(), request.getUsername()));
        }
        Brand brand = new Brand();
        brand.setId(request.getId());
        brand.setFullname(request.getFullname());
        brand.setEmail(request.getEmail());
        Job job = jobRepository.findByIdJob("B");
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
        Set<Role> temp_role = roleRepository.findByName("Brand");
        userBrand.setRoles(temp_role);
        brand.setUserBrand(userBrand);
        emailService.sendEmail(request.getEmail(), request.getUsername(), generatedString);
        brandRepository.save(brand);
        userBrandRepository.save(userBrand);
        return new ResponseMessage<>("Brand Registered", new RegisterBrandRequest(request.getId(), request.getFullname(), request.getEmail(), request.getUsername()));
    }
    
    public ResponseMessage<RegisterInfluencerResponse> regist(RegisterInfluencerRequest request) {
        Job job = jobRepository.findByIdJob("I");
        long temp = influencerRepository.findEmail(request.getEmail());
        if (temp >= 1) {
            return new ResponseMessage<>("Error -  Email Already Registered", new RegisterInfluencerResponse(request.getId(), request.getFullname(), request.getEmail(), job, request.getUsername(), "ungenerated"));
        }
        long tempUser = userRepository.countByUsername(request.getUsername());
        if (tempUser >= 1) {
            return new ResponseMessage<>("Error -  Username Already Registered", new RegisterInfluencerResponse(request.getId(), request.getFullname(), request.getEmail(), job, request.getUsername(), "ungenerated"));
        }
        Influencer influencer = new Influencer();
        influencer.setId(request.getId());
        influencer.setFullname(request.getFullname());
        influencer.setEmail(request.getEmail());
        influencer.setBirthDate(request.getBirthDate());
        influencer.setCity(request.getCity());
        Set<InfluenceType> temp_type = influenceTypeRepository.findByName(request.getInfluenceType());
        influencer.setInfluenceTypes(temp_type);
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
        influencer.setUser(user);
        emailService.sendEmail(request.getEmail(), request.getUsername(), generatedString);
        influencerRepository.save(influencer);
        userRepository.save(user);
        return new ResponseMessage<>("Influencer Registered", new RegisterInfluencerResponse(request.getId(), request.getFullname(), request.getEmail(), job, request.getUsername(), generatedString));
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
        Set<Role> temp_role = roleRepository.findByName("Influencer");
        user.setRoles(temp_role);
        userRepository.save(user);
        emailService.forgotEmail(request.getEmail(), temp_user.getUsername(), generatedString);
        return new ForgotPasswordRequest(request.getEmail());
    }

    public ForgotPasswordRequest forgotBrand(ForgotPasswordRequest request){
        Long temp = brandRepository.findIdByEmail(request.getEmail());
        if (temp == null) {
            throw dataNotFound();
        }
        UserBrand temp_user = userBrandRepository.findUsernameById(temp);
        System.out.println(temp_user);
        UserBrand user = new UserBrand();
        user.setId(temp);
        user.setUsername(temp_user.getUsername());
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        System.out.println("Hasil Generate : " + generatedString);
        user.setPassword(encoder.encode(generatedString));
        Set<Role> temp_role = roleRepository.findByName("Brand");
        user.setRoles(temp_role);
        userBrandRepository.save(user);
        emailService.forgotEmail(request.getEmail(), temp_user.getUsername(), generatedString);
        return new ForgotPasswordRequest(request.getEmail());
    }

    public String influencerChangePassword(InfluencerChangePasswordRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        System.out.println(encoder.matches(request.getOldPassword(), user.getPassword()));
        if(!encoder.matches(request.getOldPassword(), user.getPassword())){
            return "Password Salah";
        }
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setPassword(encoder.encode(request.getNewPassword()));
        String pass = request.getNewPassword();
        userRepository.save(user);
        return "You're new password : " + pass;
    }
}
