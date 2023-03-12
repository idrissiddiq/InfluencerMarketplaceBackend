package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "tb_admin")
public class Admin {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job Brandjob;

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @PrimaryKeyJoinColumn
    private UserAdmin userAdmin;

    public Admin(){

    }

    public Admin(Long id, String fullname, String email, Job brandjob, UserAdmin userAdmin) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        Brandjob = brandjob;
        this.userAdmin = userAdmin;
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

    public UserAdmin getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(UserAdmin userAdmin) {
        this.userAdmin = userAdmin;
    }
}
