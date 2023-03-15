/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.config;

import com.InfluencerMarketpalce.serverside.service.AppUserAdminDetailService;
import com.InfluencerMarketpalce.serverside.service.AppUserBrandDetailService;
import com.InfluencerMarketpalce.serverside.service.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Sendy
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserDetailService appUserDetailService;
    private AppUserBrandDetailService appUserBrandDetailService;
    private AppUserAdminDetailService appUserAdminDetailService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(AppUserDetailService appUserDetailService, AppUserBrandDetailService appUserBrandDetailService, AppUserAdminDetailService appUserAdminDetailService, PasswordEncoder passwordEncoder) {
        this.appUserDetailService = appUserDetailService;
        this.appUserBrandDetailService = appUserBrandDetailService;
        this.appUserAdminDetailService = appUserAdminDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login/**").permitAll()
                .antMatchers("/api/register/**").permitAll()
                .antMatchers("/api/influencer/findAllSortByRate").permitAll()
                .antMatchers("/api/influencer/findAgeSortByRate").permitAll()
                .antMatchers("/api/influencer/findAllInfluencer").permitAll()
                .antMatchers("/api/influencer/profile/photo/**").permitAll()
                .antMatchers("/api/campaign/1").permitAll()
                .antMatchers("/api/campaign/open").permitAll()
                .antMatchers("/api/influencerType").permitAll()
                //                .antMatchers("/api/**").permitAll()
                .antMatchers("/**", "/logout").authenticated()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(appUserBrandDetailService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(appUserAdminDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); //To change body of generated methods, choose Tools | Templates.
    }

}
