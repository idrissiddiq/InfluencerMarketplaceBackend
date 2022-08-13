package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "tb_instagram")
public class Instagram {
    @Id
    @Column(name = "influencer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "instagram_id")
    private String igId;

    @Column(name = "link")
    private String link;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "influencer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Influencer influencer;

    public Instagram(){

    }

    public Instagram(Long id, String igId, String link, Influencer influencer) {
        this.id = id;
        this.igId = igId;
        this.link = link;
        this.influencer = influencer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIgId() {
        return igId;
    }

    public void setIgId(String igId) {
        this.igId = igId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Influencer getInfluencer() {
        return influencer;
    }

    public void setInfluencer(Influencer influencer) {
        this.influencer = influencer;
    }
}
