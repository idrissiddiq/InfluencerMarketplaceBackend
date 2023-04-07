package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.request.ChangeProfilePhotoRequest;
import com.InfluencerMarketpalce.serverside.model.request.EditProfileBrand;
import com.InfluencerMarketpalce.serverside.model.request.EditProfileInfluencer;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.repository.*;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class BrandService extends ResponseStatus {

    private BrandRepository brandRepository;
    private BrandFilePathRepository brandFilePathRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository, BrandFilePathRepository brandFilePathRepository) {
        this.brandRepository = brandRepository;
        this.brandFilePathRepository = brandFilePathRepository;
    }
}
