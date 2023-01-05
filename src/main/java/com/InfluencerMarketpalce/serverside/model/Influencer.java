package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tb_influencer")
public class Influencer {

    @Id
    @Column(name = "influencer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "city")
    private String city;

    @Column(name = "rate")
    private Long rate;

    @Column(name = "campaign_done")
    private Long cd;

    @Column(name = "engagement_rate")
    private String er;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "youtube")
    private String youtube;

    @Column(name = "tiktok")
    private String tiktok;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToOne(mappedBy = "influencer", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @PrimaryKeyJoinColumn
    private User user;

    @OneToOne(mappedBy = "influencer", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @PrimaryKeyJoinColumn
    private InfluencerFilePath influencerFilePath;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "influencer_type",
            joinColumns = @JoinColumn(name = "influencer_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<InfluencerType> influenceTypes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "influencer_campaign",
            joinColumns = @JoinColumn(name = "inflencer_id"),
            inverseJoinColumns = @JoinColumn(name = "campaign_id"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Campaign> campaigns;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Contract> contracts;


    public Influencer() {
    }

    public Influencer(Long id, String fullname, String email, LocalDate birthDate, String city, Long rate, String er, String instagram, String youtube, String tiktok, Job job, User user, InfluencerFilePath influencerFilePath, Set<InfluencerType> influenceTypes, Set<Campaign> campaigns, Set<Contract> contracts) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.birthDate = birthDate;
        this.city = city;
        this.rate = rate;
        this.er = er;
        this.instagram = instagram;
        this.youtube = youtube;
        this.tiktok = tiktok;
        this.job = job;
        this.user = user;
        this.influencerFilePath = influencerFilePath;
        this.influenceTypes = influenceTypes;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Long getCd() {
        return cd;
    }

    public void setCd(Long cd) {
        this.cd = cd;
    }

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<InfluencerType> getInfluenceTypes() {
        return influenceTypes;
    }

    public void setInfluenceTypes(Set<InfluencerType> influenceTypes) {
        this.influenceTypes = influenceTypes;
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

    public InfluencerFilePath getInfluencerFilePath() {
        return influencerFilePath;
    }

    public void setInfluencerFilePath(InfluencerFilePath influencerFilePath) {
        this.influencerFilePath = influencerFilePath;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }
}
//done
