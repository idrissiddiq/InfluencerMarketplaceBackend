package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_brand")
public class Brand {
    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job Brandjob;

    @OneToOne(mappedBy = "brand", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @PrimaryKeyJoinColumn
    private UserBrand userBrand;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Campaign> campaigns;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Contract> contracts;

    public Brand(){

    }

    public Brand(Long id, String fullname, String email, Job brandjob, UserBrand userBrand, Set<Campaign> campaigns, Set<Contract> contracts) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        Brandjob = brandjob;
        this.userBrand = userBrand;
        this.campaigns = campaigns;
        this.contracts = contracts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Job getBrandjob() {
        return Brandjob;
    }

    public void setBrandjob(Job brandjob) {
        Brandjob = brandjob;
    }

    public UserBrand getUserBrand() {
        return userBrand;
    }

    public void setUserBrand(UserBrand userBrand) {
        this.userBrand = userBrand;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }
}
