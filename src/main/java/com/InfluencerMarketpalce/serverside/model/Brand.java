package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "tb_brand")
public class Brand {
    @Id
    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    private User user;

    public Brand(){

    }

    public Brand(Long id, String fullname, String email, Job brandjob, User user) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.Brandjob = brandjob;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
