package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_influence_type")
public class InfluencerType {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "influenceTypes", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Influencer> influencers;

    public InfluencerType(){

    }

    public InfluencerType(Long id, String name, Set<Influencer> influencers) {
        this.id = id;
        this.name = name;
        this.influencers = influencers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Influencer> getInfluencers() {
        return influencers;
    }

    public void setInfluencers(Set<Influencer> influencers) {
        this.influencers = influencers;
    }
}
