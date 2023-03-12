package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Brand;
import com.InfluencerMarketpalce.serverside.model.Influencer;
import com.InfluencerMarketpalce.serverside.model.request.ChangeProfilePhotoRequest;
import com.InfluencerMarketpalce.serverside.model.request.EditProfileBrand;
import com.InfluencerMarketpalce.serverside.model.request.EditProfileInfluencer;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.service.BrandService;
import com.InfluencerMarketpalce.serverside.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/profile")
    public ResponseData<Brand> profile() {
        return new ResponseData(brandService.profile());
    }

    @PutMapping("/profile")
    public ResponseMessage editProfile(@RequestBody EditProfileBrand request){
        return brandService.editProfile(request);
    }

    @GetMapping("/profile/photo")
    public ResponseMessage getMyProfilePhotoPath(){
        return brandService.getMyProfilePhotoPath();
    }

    @PutMapping("/profile/photo")
    public ResponseMessage editProfilePhoto(@RequestBody ChangeProfilePhotoRequest request){
        return brandService.editProfilePoto(request);
    }
}
