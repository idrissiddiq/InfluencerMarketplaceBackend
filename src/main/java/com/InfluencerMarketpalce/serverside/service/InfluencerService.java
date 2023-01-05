/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.request.ChangeProfilePhotoRequest;
import com.InfluencerMarketpalce.serverside.model.request.EditProfileInfluencer;
import com.InfluencerMarketpalce.serverside.model.response.CalculateAgeReponse;
import com.InfluencerMarketpalce.serverside.model.response.FindAllInfluencerResponse;
import com.InfluencerMarketpalce.serverside.repository.*;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class InfluencerService extends ResponseStatus {

    private InfluencerRepository influencerRepository;
    private JobRepository jobRepository;
    private InfluenceTypeRepository influenceTypeRepository;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private InfluencerFilePathRepository influencerFilePathRepository;

    @Autowired
    public InfluencerService(InfluencerRepository influencerRepository, JobRepository jobRepository, InfluenceTypeRepository influenceTypeRepository, UserRepository userRepository, PasswordEncoder encoder, InfluencerFilePathRepository influencerFilePathRepository) {
        this.influencerRepository = influencerRepository;
        this.jobRepository = jobRepository;
        this.influenceTypeRepository = influenceTypeRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.influencerFilePathRepository = influencerFilePathRepository;
    }

    public Influencer profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Influencer data = influencerRepository.findById(user.getId()).orElseThrow(this::dataNotFound);
        return data;
    }

    public List<InfluencerType> findMyType() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        List<Long> temp = influenceTypeRepository.findMyTypeId(user.getId());
        List<InfluencerType> data = new ArrayList<>();
        for(int i = 0; i < temp.size() ; i++){
            data.add(influenceTypeRepository.getMyTypeName(temp.get(i)));
        }
        return data;
    }

    public ResponseMessage editProfile(EditProfileInfluencer request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Optional<Influencer> temp = influencerRepository.findAllByEmail(request.getEmail());
        if (temp.isPresent() && temp.get().getEmail() != user.getInfluencer().getEmail()) {
            return new ResponseMessage<>("Error -  Email Already Registered", new EditProfileInfluencer(request.getFullname(), request.getEmail(), request.getCity(), request.getBirthDate(), request.getInfluenceType()));
        }
//        Optional<User> tempUser = userRepository.findAllByUsername(request.getUsername());
//        if (tempUser.isPresent() && tempUser.get().getUsername() != user.getUsername()) {
//            return new ResponseMessage<>("Error -  Username Already Registered", new EditProfileInfluencer(request.getFullname(), request.getEmail(), request.getCity(), request.getBirthDate(), request.getInfluenceType(), request.getUsername()));
//        }
        Influencer influencer = new Influencer();
        influencer.setId(user.getId());
        influencer.setFullname(request.getFullname());
        influencer.setBirthDate(request.getBirthDate());
        influencer.setCity(request.getCity());
        influencer.setEmail(request.getEmail());
        Set<InfluencerType> temp_type = influenceTypeRepository.findByName(request.getInfluenceType());
        influencer.setInfluenceTypes(temp_type);
//        influencer.setCampaigns(user.getInfluencer().getCampaigns());
        Job job = jobRepository.findByIdJob("I");
        influencer.setJob(job);
        influencerRepository.save(influencer);
        return new ResponseMessage<>("Influencer Profile Updated",new EditProfileInfluencer(request.getFullname(), request.getEmail(), request.getCity(), request.getBirthDate(), request.getInfluenceType()));
    }

    public ResponseMessage editProfilePoto(ChangeProfilePhotoRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Influencer temp = influencerRepository.getById(user.getId());
//        if (temp.equals(null)) {
//            return new ResponseMessage<>("Error -  Account Not Found", new ChangeProfilePhotoRequest(request.getPath()));
//        }
        InfluencerFilePath influencerFilePath = new InfluencerFilePath();
        influencerFilePath.setId(user.getId());
        influencerFilePath.setProfile(request.getPath());
        influencerFilePathRepository.save(influencerFilePath);
        return new ResponseMessage<>("Profile Photo Updated",new ChangeProfilePhotoRequest(request.getPath()));
    }

    public ResponseMessage<ChangeProfilePhotoRequest> getMyProfilePhotoPath(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        InfluencerFilePath influencerFilePath = influencerFilePathRepository.getMyProfilePhotoPath(user.getId());
//        if (influencerFilePath.equals(null)) {
//            return new ResponseMessage<>("Error -  Account Not Found", new ChangeProfilePhotoRequest(request.getPath()));
//        }
        return new ResponseMessage<>("Profile Photo Updated",new ChangeProfilePhotoRequest(influencerFilePath.getProfile()));
    }

    public InfluencerFilePath getProfilePhotoById(Long id){
        InfluencerFilePath influencerFilePath = influencerFilePathRepository.getMyProfilePhotoPath(id);
        return influencerFilePath;
    }

    public List<Influencer> findAll() {
        return influencerRepository.findAll();
    }
    
    public List<Influencer> findAllExcept() {
        return influencerRepository.findAllExceptAdmin();
    }

    public List<Influencer> findAllSortByRate() {
        return influencerRepository.findAllSortByRate();
    }

    public List<FindAllInfluencerResponse> findAllInfluencerResponse(){
        return influencerRepository.findAllInfluencer();
    }

    public List<CalculateAgeReponse> findAgeSortByRate() {
        return influencerRepository.findAgeSortByRate();
    }


}
