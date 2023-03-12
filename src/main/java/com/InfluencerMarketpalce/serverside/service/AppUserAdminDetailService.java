package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.AppUserAdminDetail;
import com.InfluencerMarketpalce.serverside.model.AppUserBrandDetail;
import com.InfluencerMarketpalce.serverside.model.UserAdmin;
import com.InfluencerMarketpalce.serverside.model.UserBrand;
import com.InfluencerMarketpalce.serverside.repository.UserAdminRepository;
import com.InfluencerMarketpalce.serverside.repository.UserBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserAdminDetailService implements UserDetailsService {
    private UserAdminRepository userAdminRepository;

    @Autowired
    public AppUserAdminDetailService(UserAdminRepository userAdminRepository) {
        this.userAdminRepository = userAdminRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAdmin userAdmin = userAdminRepository.findByUsername(username);
        if(userAdmin == null){
            throw new UsernameNotFoundException("User tidak ditemukan");
        }

        return new AppUserAdminDetail(userAdmin);
    }
}
