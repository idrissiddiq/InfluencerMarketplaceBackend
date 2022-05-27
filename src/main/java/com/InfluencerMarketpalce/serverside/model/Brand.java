package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

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

    public Brand(){

    }

    public Brand(Long id, String fullname, String email, Job brandjob, UserBrand userBrand) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.Brandjob = brandjob;
        this.userBrand = userBrand;
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
        this.Brandjob = brandjob;
    }

    public UserBrand getUser() {
        return userBrand;
    }

    public void setUser(UserBrand userBrand) {
        this.userBrand = userBrand;
    }
}
