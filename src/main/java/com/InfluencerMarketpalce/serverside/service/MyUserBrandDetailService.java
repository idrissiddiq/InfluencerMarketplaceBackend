package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.MyUserBrandDetail;
import com.InfluencerMarketpalce.serverside.model.MyUserDetail;
import com.InfluencerMarketpalce.serverside.model.User;
import com.InfluencerMarketpalce.serverside.model.UserBrand;
import com.InfluencerMarketpalce.serverside.repository.UserBrandRepository;
import com.InfluencerMarketpalce.serverside.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserBrandDetailService implements UserDetailsService {
    private UserBrandRepository userBrandRepository;

    public MyUserBrandDetailService(UserBrandRepository userBrandRepository) {
        this.userBrandRepository = userBrandRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBrand userBrand = userBrandRepository.findByUsername(username);
        if (userBrand == null) {
            throw new UsernameNotFoundException("Username Unidentified!");
        } else {
            return new MyUserBrandDetail(userBrand);
        }
    }
}
