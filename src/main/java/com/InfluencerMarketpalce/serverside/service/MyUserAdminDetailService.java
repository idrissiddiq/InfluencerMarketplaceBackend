package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.MyUserAdminDetail;
import com.InfluencerMarketpalce.serverside.model.MyUserBrandDetail;
import com.InfluencerMarketpalce.serverside.model.UserAdmin;
import com.InfluencerMarketpalce.serverside.model.UserBrand;
import com.InfluencerMarketpalce.serverside.repository.UserAdminRepository;
import com.InfluencerMarketpalce.serverside.repository.UserBrandRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserAdminDetailService implements UserDetailsService {
    private UserAdminRepository userAdminRepository;

    public MyUserAdminDetailService(UserAdminRepository userAdminRepository) {
        this.userAdminRepository = userAdminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAdmin userAdmin = userAdminRepository.findByUsername(username);
        if (userAdmin == null) {
            throw new UsernameNotFoundException("Username Unidentified!");
        } else {
            return new MyUserAdminDetail(userAdmin);
        }
    }
}
