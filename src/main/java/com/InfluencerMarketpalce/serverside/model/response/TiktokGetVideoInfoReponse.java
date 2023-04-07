package com.InfluencerMarketpalce.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TiktokGetVideoInfoReponse {
    private Long comment_count;
    private Long digg_count;
    private Long download_count;
    private Long play_count;
    private Long share_count;
    private Long forward_count;
    private Long whatsapp_share_count;
    private Long collect_count;
}
