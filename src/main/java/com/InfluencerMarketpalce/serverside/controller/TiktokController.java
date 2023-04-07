package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
import com.InfluencerMarketpalce.serverside.model.response.TiktokGetVideoInfoReponse;
import com.InfluencerMarketpalce.serverside.service.TiktokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/tiktok")
public class TiktokController {
    @Autowired
    private TiktokService tiktokService;

    @PostMapping("/getVideoinfo")
    public ResponseData<TiktokGetVideoInfoReponse> getVideoInfo(@RequestBody Map<String, Object> param){
        return tiktokService.getVideoInfo(param);
    }
}
