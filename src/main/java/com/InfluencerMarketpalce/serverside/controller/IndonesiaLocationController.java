package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.response.IndonesiaLocationResponse;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.service.IndonesiaLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/location")
public class IndonesiaLocationController {
    @Autowired
    private IndonesiaLocationService indonesiaLocationService;

    @GetMapping
    public ResponseListData<IndonesiaLocationResponse> province(){
        return indonesiaLocationService.province();
    }

    @GetMapping("/city")
    public ResponseListData<IndonesiaLocationResponse> city(@RequestParam("id") String id){
        return indonesiaLocationService.city(id);
    }

    @GetMapping("/kecamatan/{id}")
    public ResponseListData<IndonesiaLocationResponse> kecamatan(@PathVariable String id){
        return indonesiaLocationService.kecamatan(id);
    }

    @GetMapping("/kelurahan/{id}")
    public ResponseListData<IndonesiaLocationResponse> kelurahan(@PathVariable String id){
        return indonesiaLocationService.kelurahan(id);
    }
}
