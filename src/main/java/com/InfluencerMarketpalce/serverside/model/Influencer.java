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

    @Column(name = "province")
    private String province;

    @Column(name = "kecamatan")
    private String kecamatan;

    @Column(name = "kelurahan")
    private String kelurahan;

    @Column(name = "detail_address")
    private String detailAddress;

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

    @Column(name = "facebook")
    private String facebook;

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

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Report> reports;

    @OneToMany(mappedBy = "influencer", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Notification> notifications;


    public Influencer() {
    }

    public Influencer(Long id, String fullname, String email, LocalDate birthDate, String city, String province, String kecamatan, String kelurahan, String detailAddress, Long rate, Long cd, String er, String instagram, String youtube, String tiktok, String facebook, Job job, User user, InfluencerFilePath influencerFilePath, Set<InfluencerType> influenceTypes, Set<Campaign> campaigns, Set<Contract> contracts) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.birthDate = birthDate;
        this.city = city;
        this.province = province;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.detailAddress = detailAddress;
        this.rate = rate;
        this.cd = cd;
        this.er = er;
        this.instagram = instagram;
        this.youtube = youtube;
        this.tiktok = tiktok;
        this.facebook = facebook;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
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

    public InfluencerFilePath getInfluencerFilePath() {
        return influencerFilePath;
    }

    public void setInfluencerFilePath(InfluencerFilePath influencerFilePath) {
        this.influencerFilePath = influencerFilePath;
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
}
//done
