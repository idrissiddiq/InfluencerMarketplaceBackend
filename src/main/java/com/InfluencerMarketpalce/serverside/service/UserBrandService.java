package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.UserBrand;
import com.InfluencerMarketpalce.serverside.repository.BrandRepository;
import com.InfluencerMarketpalce.serverside.repository.UserBrandRepository;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBrandService extends ResponseStatus {
    private UserBrandRepository userBrandRepository;
    private BrandRepository brandRepository;

    @Autowired
    public UserBrandService(UserBrandRepository userBrandRepository) {
        this.userBrandRepository = userBrandRepository;
    }

    public List<UserBrand> findAll() {
        return userBrandRepository.findAll();
    }

    public UserBrand findById(Long id) {
        UserBrand data = userBrandRepository.findById(id).orElseThrow(this::dataNotFound);
        return data;
    }

    public UserBrand create(UserBrand userBrand) {
        return userBrandRepository.save(userBrand);
    }

    public UserBrand delete(Long id) {
        UserBrand data = userBrandRepository.findById(id).orElseThrow(this::dataNotFound);
        userBrandRepository.deleteById(id);
        return data;
    }

    public UserBrand update(UserBrand userBrand, Long id) {
        Optional<UserBrand> temp = userBrandRepository.findById(id);
        if (!temp.isPresent()) {
            throw dataNotFound();
        }
        return userBrandRepository.save(userBrand);
    }
}
