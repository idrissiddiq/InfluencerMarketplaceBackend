package com.InfluencerMarketpalce.serverside.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePostMessage {
    private String responseCode;
    private String message;
    private Map<String, Object> data;
}
