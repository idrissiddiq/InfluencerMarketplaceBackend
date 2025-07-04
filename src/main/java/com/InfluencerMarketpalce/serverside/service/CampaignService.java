package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.request.CreateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.request.UpdateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.model.response.FindAllOpenCampaignResponse;
import com.InfluencerMarketpalce.serverside.repository.BrandRepository;
import com.InfluencerMarketpalce.serverside.repository.CampaignRepository;
import com.InfluencerMarketpalce.serverside.repository.CampaignStatusRepository;
import com.InfluencerMarketpalce.serverside.repository.UserBrandRepository;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService extends ResponseStatus {
    private CampaignRepository campaignRepository;
    private UserBrandRepository userBrandRepository;
    private BrandRepository brandRepository;
    private CampaignStatusRepository campaignStatusRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, UserBrandRepository userBrandRepository, BrandRepository brandRepository, CampaignStatusRepository campaignStatusRepository) {
        this.campaignRepository = campaignRepository;
        this.userBrandRepository = userBrandRepository;
        this.brandRepository = brandRepository;
        this.campaignStatusRepository = campaignStatusRepository;
    }

    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    public Campaign findById(Long id) {
        Campaign data = campaignRepository.findById(id).orElseThrow(this::dataNotFound);
        return data;
    }

    public List<Campaign> findAllByBrand() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        return campaignRepository.findAllByBrand(userBrand.getId());
    }

    public List<Campaign> findAllByStatus(Long id) {
        return campaignRepository.findAllByStatus(id);
    }

    public List<FindAllOpenCampaignResponse> findAllOpenCampaign() {
        return campaignRepository.findAllCampaignOpen();
    }

    public List<Campaign> findAllByBrandStatus(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        return campaignRepository.findAllByBrandStatus(userBrand.getId(), id);
    }

    public ResponseMessage<CreateCampaignRequest> createCampaign(CreateCampaignRequest request){
        Campaign campaign = new Campaign();
        campaign.setTitle(request.getTitle());
        campaign.setDescription(request.getDescription());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        Brand brand = brandRepository.getById(userBrand.getId());
        campaign.setBrand(brand);
        CampaignStatus campaignStatus = campaignStatusRepository.getById(1L);
        campaign.setCampaignStatus(campaignStatus);
        campaignRepository.save(campaign);
        return new ResponseMessage<>("Campaign Created",new CreateCampaignRequest(request.getTitle(), request.getDescription()));
    }

    public ResponseMessage updateCampaign(UpdateCampaignRequest request, Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        Optional<Campaign> temp = campaignRepository.findAllByBrandId(userBrand.getId(), id);
        if (!temp.isPresent()) {
            throw dataNotFound();
        }
        Campaign data = campaignRepository.findById(id).orElseThrow(this::dataNotFound);
        if(userBrand.getId() != data.getBrand().getId()){
            throw dataNotFound();
        }
        Campaign campaign = new Campaign();
        campaign.setId(id);
        campaign.setTitle(request.getTitle());
        campaign.setDescription((request.getDescription()));
        Brand brand = brandRepository.getById(data.getBrand().getId());
        campaign.setBrand(brand);
        CampaignStatus campaignStatus = campaignStatusRepository.getById(request.getcampaignStatus());
        campaign.setCampaignStatus(campaignStatus);
        campaignRepository.save(campaign);
        return new ResponseMessage<>("Campaign Updated",new UpdateCampaignRequest(request.getTitle(), request.getDescription(), request.getcampaignStatus()));
    }

    public Campaign delete(Authentication authentication, Long id) {
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        Campaign data = campaignRepository.findAllByBrandId(userBrand.getId(), id).orElseThrow(this::dataNotFound);
        if(!data.equals(null)){
            campaignRepository.deleteById(id);
            return data;
        } else{
            return data;
        }


    }
}
