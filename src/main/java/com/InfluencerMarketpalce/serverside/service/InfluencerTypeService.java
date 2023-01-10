package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.InfluencerType;
import com.InfluencerMarketpalce.serverside.repository.InfluenceTypeRepository;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfluencerTypeService extends ResponseStatus {
    private InfluenceTypeRepository influenceTypeRepository;

    @Autowired
    public InfluencerTypeService(InfluenceTypeRepository influenceTypeRepository) {
        this.influenceTypeRepository = influenceTypeRepository;
    }

    public List<InfluencerType> getAll(){
        List<InfluencerType> data = influenceTypeRepository.findAll();
        return data;
    }
}
