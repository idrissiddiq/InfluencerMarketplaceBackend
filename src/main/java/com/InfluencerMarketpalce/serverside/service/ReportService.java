package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.model.response.ResponseMessage;
import com.InfluencerMarketpalce.serverside.repository.*;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportService extends ResponseStatus {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractStatusRepository contractStatusRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private UserAdminRepository userAdminRepository;

    public String MonthlyReport(Map<String, Object> param){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Influencer influencer = influencerRepository.getById(user.getId());
        JSONArray allData = new JSONArray(param);
        if (allData.isEmpty()){
            return "Data is Empty";
        }
        for(int i = 0; i <= allData.length(); i++){
            JSONObject data = new JSONObject(allData.optString(i));
            Report report = new Report();
            report.setLink(data.optString("link"));
            report.setLike(Long.parseLong(data.optString("like")));
            report.setCommment(Long.parseLong(data.optString("comment")));
            report.setImpression(Long.parseLong(data.optString("impression")));
            report.setReach(Long.parseLong(data.optString("reach")));
            report.setSave(Long.parseLong(data.optString("save")));
            report.setShare(Long.parseLong(data.optString("share")));
            report.setView(Long.parseLong(data.optString("view")));
            report.setCreateBy(user.getUsername());
            report.setInfluencer(influencer);
            Contract contract = contractRepository.findByInfluencer(influencer.getId(), Long.parseLong(data.optString("contractId")));
            report.setContract(contract);
            report.setCampaign(campaignRepository.getById(contract.getCampaign().getId()));
            reportRepository.save(report);
        }
        return "Create Report Success";
    }

    public String createReport(Map<String, Object> param){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Influencer influencer = influencerRepository.getById(user.getId());
        if (user.getUsername().isEmpty()) {
            return "You have no right to do this action";
        }
        Report report = new Report();
        report.setLink(param.get("link").toString());
        report.setCreateBy(user.getUsername());
        report.setInfluencer(influencer);
        report.setCreateDate(LocalDate.now().toString());
        Contract contract = contractRepository.findByInfluencer(influencer.getId(), Long.parseLong(param.get("contractId").toString()));
        if(contract.getContractStatus().getId() != 2){
            return "Contract Status is Not on Going";
        }
        Optional<Contract> check = contractRepository.checkContractById(Long.parseLong(param.get("contractId").toString()));
        if(!check.isPresent()){
            return "Contract Does Not Exist";
        }
        ContractStatus contractStatus = contractStatusRepository.getById(3L);
        report.setContract(contract);
        report.setCampaign(campaignRepository.getById(contract.getCampaign().getId()));
        report.setBrand(contract.getBrand());
        contract.setContractStatus(contractStatus);
        reportRepository.save(report);
        contractRepository.save(contract);
        return "Create Report Success";
    }

    public List<Report> findAllMonthlyReport(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Influencer influencer = influencerRepository.getById(user.getId());
        return reportRepository.findAllMonthlyReportbyInfluencer(influencer.getId());
    }

    public List<Report> findAllReportByBrand(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Long userAdmin = userAdminRepository.countByUsername(name);
        if(userAdmin <= 0){
            return new ArrayList<>();
        }
        return reportRepository.findAllReportbyBrand(id);
    }
}
