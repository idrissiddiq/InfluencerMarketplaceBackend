package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_influencer_file_path")
public class InfluencerFilePath {
    @Id
    @Column(name = "influencer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "profile_photo")
    private String profile;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "influencer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Influencer influencer;

    public InfluencerFilePath(){

    }

    public InfluencerFilePath(Long id, String profile, Influencer influencer) {
        this.id = id;
        this.profile = profile;
        this.influencer = influencer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Influencer getInfluencer() {
        return influencer;
    }

    public void setInfluencer(Influencer influencer) {
        this.influencer = influencer;
    }
}
