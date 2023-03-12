package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.AppUserBrandDetail;
import com.InfluencerMarketpalce.serverside.model.UserBrand;
import com.InfluencerMarketpalce.serverside.repository.UserBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserBrandDetailService implements UserDetailsService {
    private UserBrandRepository userBrandRepository;

    @Autowired
    public AppUserBrandDetailService(UserBrandRepository userBrandRepository) {
        this.userBrandRepository = userBrandRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBrand userBrand = userBrandRepository.findByUsername(username);
        if(userBrand == null){
            throw new UsernameNotFoundException("User tidak ditemukan");
        }

        return new AppUserBrandDetail(userBrand);
    }
}
