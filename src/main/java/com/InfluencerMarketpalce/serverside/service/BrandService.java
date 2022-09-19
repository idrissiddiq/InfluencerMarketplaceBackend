package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.request.EditProfileBrand;
import com.InfluencerMarketpalce.serverside.model.request.EditProfileInfluencer;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.repository.BrandRepository;
import com.InfluencerMarketpalce.serverside.repository.InfluencerRepository;
import com.InfluencerMarketpalce.serverside.repository.JobRepository;
import com.InfluencerMarketpalce.serverside.repository.UserBrandRepository;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BrandService extends ResponseStatus {

    private BrandRepository brandRepository;
    private UserBrandRepository userBrandRepository;
    private JobRepository jobRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository, UserBrandRepository userBrandRepository, JobRepository jobRepository) {
        this.brandRepository = brandRepository;
        this.userBrandRepository = userBrandRepository;
        this.jobRepository = jobRepository;
    }

    public Brand profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        Brand data = brandRepository.findById(userBrand.getId()).orElseThrow(this::dataNotFound);
        return data;
    }

    public ResponseMessage editProfile(EditProfileBrand request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        Optional<Brand> temp = brandRepository.findAllByEmail(request.getEmail());
        if (temp.isPresent() && temp.get().getEmail() != userBrand.getBrand().getEmail()) {
            return new ResponseMessage<>("Error -  Email Already Registered", new EditProfileBrand(request.getFullname(), request.getEmail(), request.getUsername()));
        }
        Optional<UserBrand> tempUser = userBrandRepository.findAllByUsername(request.getUsername());
        if (tempUser.isPresent() && tempUser.get().getUsername() != userBrand.getUsername()) {
            return new ResponseMessage<>("Error -  Username Already Registered", new EditProfileBrand(request.getFullname(), request.getEmail(), request.getUsername()));
        }
        Brand brand = new Brand();
        brand.setId(userBrand.getId());
        brand.setFullname(request.getFullname());
        brand.setEmail(request.getEmail());
        Job job = jobRepository.findByIdJob("B");
        brand.setBrandjob(job);
//        influencer.setContracts(user.getInfluencer().getContracts());
//        influencer.setUser(user);
        brandRepository.save(brand);
        return new ResponseMessage<>("Influencer Profile Updated",new EditProfileBrand(request.getFullname(), request.getEmail(), request.getUsername()));
    }
}
