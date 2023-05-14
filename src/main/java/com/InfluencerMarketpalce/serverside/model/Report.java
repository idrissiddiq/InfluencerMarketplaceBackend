package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_report")
public class Report {
    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "comment")
    private Long commment;

    @Column(name = "like")
    private Long like;

    @Column(name = "share")
    private Long share;

    @Column(name = "save")
    private Long save;

    @Column(name = "impression")
    private Long impression;

    @Column(name = "reach")
    private Long reach;

    @Column(name = "view")
    private Long view;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createDate;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "influencer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Influencer influencer;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}
