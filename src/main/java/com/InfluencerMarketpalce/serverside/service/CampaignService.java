package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.response.FindAllOpenCampaignResponse;
import com.InfluencerMarketpalce.serverside.model.response.FindAllOpenCampaignTableResponse;
import com.InfluencerMarketpalce.serverside.model.response.ResponsePostMessage;
import com.InfluencerMarketpalce.serverside.repository.BrandRepository;
import com.InfluencerMarketpalce.serverside.repository.CampaignRepository;
import com.InfluencerMarketpalce.serverside.repository.CampaignStatusRepository;
import com.InfluencerMarketpalce.serverside.repository.UserAdminRepository;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CampaignService extends ResponseStatus {
    private CampaignRepository campaignRepository;
    private BrandRepository brandRepository;
    private CampaignStatusRepository campaignStatusRepository;
    private UserAdminRepository userAdminRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, BrandRepository brandRepository, CampaignStatusRepository campaignStatusRepository, UserAdminRepository userAdminRepository) {
        this.campaignRepository = campaignRepository;
        this.brandRepository = brandRepository;
        this.campaignStatusRepository = campaignStatusRepository;
        this.userAdminRepository = userAdminRepository;
    }

    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    public Campaign findById(Long id) {
        Campaign data = campaignRepository.findById(id).orElseThrow(this::dataNotFound);
        return data;
    }

    public List<Campaign> findAllByStatus(Long id) {
        return campaignRepository.findAllByStatus(id);
    }

    public List<FindAllOpenCampaignResponse> findAllOpenCampaign() {
        return campaignRepository.findAllCampaignOpen();
    }

    public List<FindAllOpenCampaignTableResponse> findAllOpenCampaignTable() {
        return campaignRepository.findFullAllCampaignOpen();
    }

    public ResponsePostMessage createCampaign(Map<String, Object> param){
        ResponsePostMessage responsePostMessage = new ResponsePostMessage();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserAdmin userAdmin = userAdminRepository.findByUsername(name);
        if (userAdmin.getUsername().isEmpty()) {
            responsePostMessage.setResponseCode("401");
            responsePostMessage.setMessage("Unauthorized");
            responsePostMessage.setData(null);
            return responsePostMessage;
        }
        Campaign campaign = new Campaign();
        campaign.setTitle(param.get("title").toString());
        campaign.setDescription(param.get("description").toString());
        Brand brand = brandRepository.getById(Long.parseLong(param.get("id").toString()));
        campaign.setBrand(brand);
        CampaignStatus campaignStatus = campaignStatusRepository.getById(1L);
        campaign.setCampaignStatus(campaignStatus);
        campaign.setBudget(Long.parseLong(param.get("budget").toString()));
        campaign.setQuota(Long.parseLong(param.get("quota").toString()));
        campaign.setFilled(0L);
        campaign.setDos(param.get("dos").toString());
        campaign.setDont(param.get("dont").toString());
        campaignRepository.save(campaign);
        responsePostMessage.setResponseCode("200");
        responsePostMessage.setMessage("Successfully");
        responsePostMessage.setData(param);
        return responsePostMessage;
    }
//
//    public ResponseMessage updateCampaign(UpdateCampaignRequest request, Long id){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
//        String name = authentication.getName();
//        UserBrand userBrand = userBrandRepository.findByUsername(name);
//        Optional<Campaign> temp = campaignRepository.findAllByBrandId(userBrand.getId(), id);
//        if (!temp.isPresent()) {
//            throw dataNotFound();
//        }
//        Campaign data = campaignRepository.findById(id).orElseThrow(this::dataNotFound);
//        if(userBrand.getId() != data.getBrand().getId()){
//            throw dataNotFound();
//        }
//        Campaign campaign = new Campaign();
//        campaign.setId(id);
//        campaign.setTitle(request.getTitle());
//        campaign.setDescription((request.getDescription()));
//        Brand brand = brandRepository.getById(data.getBrand().getId());
//        campaign.setBrand(brand);
//        CampaignStatus campaignStatus = campaignStatusRepository.getById(request.getcampaignStatus());
//        campaign.setCampaignStatus(campaignStatus);
//        campaignRepository.save(campaign);
//        return new ResponseMessage<>("Campaign Updated",new UpdateCampaignRequest(request.getTitle(), request.getDescription(), request.getcampaignStatus()));
//    }
//
//    public Campaign delete(Authentication authentication, Long id) {
//        String name = authentication.getName();
//        UserBrand userBrand = userBrandRepository.findByUsername(name);
//        Campaign data = campaignRepository.findAllByBrandId(userBrand.getId(), id).orElseThrow(this::dataNotFound);
//        if(!data.equals(null)){
//            campaignRepository.deleteById(id);
//            return data;
//        } else{
//            return data;
//        }
//
//
//    }
}
