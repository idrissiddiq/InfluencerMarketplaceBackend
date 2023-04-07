package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_campaign")
public class Campaign {
    @Id
    @Column(name = "campaign_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Length(max = 1500)
    @Column(name = "description")
    private String description;

    @Column(name = "budget")
    private Long budget;

    @Column(name = "quota")
    private Long quota;

    @Column(name = "dos")
    private String dos;

    @Column(name = "dont")
    private String dont;

    @ManyToOne
    @JoinColumn(name = "campaign_status_id")
    private CampaignStatus campaignStatus;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToMany(mappedBy = "campaigns", cascade = CascadeType.ALL)
    private Set<Influencer> influencers;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Contract> contracts;

    public Campaign(){

    }

    public Campaign(Long id, String title, String description, Long budget, CampaignStatus campaignStatus, Brand brand, Set<Influencer> influencers, Set<Contract> contracts, String dos, String dont, Long quota) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.campaignStatus = campaignStatus;
        this.brand = brand;
        this.influencers = influencers;
        this.contracts = contracts;
        this.dont = dont;
        this.dos = dos;
        this.quota = quota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public CampaignStatus getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(CampaignStatus campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Influencer> getInfluencers() {
        return influencers;
    }

    public void setInfluencers(Set<Influencer> influencers) {
        this.influencers = influencers;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Long getQuota() {
        return quota;
    }

    public void setQuota(Long quota) {
        this.quota = quota;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getDont() {
        return dont;
    }

    public void setDont(String dont) {
        this.dont = dont;
    }
}
