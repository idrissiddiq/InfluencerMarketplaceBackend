package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.response.IndonesiaLocationResponse;
import com.InfluencerMarketpalce.serverside.model.response.ResponseData;
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

    @GetMapping("/search/{name}")
    public ResponseData<IndonesiaLocationResponse> searchProvince(@PathVariable String name){
        return indonesiaLocationService.searchProvince(name);
    }

    @GetMapping("/search/{id}/{name}")
    public ResponseData<IndonesiaLocationResponse> searchCity(@PathVariable String id,@PathVariable String name){
        return indonesiaLocationService.searchCity(id,name);
    }

    @GetMapping("/search/id/{id}")
    public ResponseData<IndonesiaLocationResponse> searchProvince(@PathVariable Long id){
        return indonesiaLocationService.searchProvince(id);
    }
}
