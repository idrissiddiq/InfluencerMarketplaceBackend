package com.InfluencerMarketpalce.serverside.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_notification")
public class Notification {
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "status")
    private String status;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createDate;

    @ManyToOne
    @JoinColumn(name = "influencer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Influencer influencer;
}
