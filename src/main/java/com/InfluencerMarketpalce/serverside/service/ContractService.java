package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.*;
import com.InfluencerMarketpalce.serverside.model.request.ApproveContractRequest;
import com.InfluencerMarketpalce.serverside.repository.*;
import com.InfluencerMarketpalce.serverside.service.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService extends ResponseStatus  {
    private ContractRepository contractRepository;
    private ContractStatusRepository contractStatusRepository;
    private ContractFilePathRepository contractFilePathRepository;
    private CampaignRepository campaignRepository;
    private UserRepository userRepository;
    private UserBrandRepository userBrandRepository;
    private UserAdminRepository userAdminRepository;
    private InfluencerRepository influencerRepository;

    @Autowired
    public ContractService(ContractRepository contractRepository, ContractStatusRepository contractStatusRepository, ContractFilePathRepository contractFilePathRepository, CampaignRepository campaignRepository, UserRepository userRepository, UserBrandRepository userBrandRepository, UserAdminRepository userAdminRepository, InfluencerRepository influencerRepository) {
        this.contractRepository = contractRepository;
        this.contractStatusRepository = contractStatusRepository;
        this.contractFilePathRepository = contractFilePathRepository;
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
        this.userBrandRepository = userBrandRepository;
        this.userAdminRepository = userAdminRepository;
        this.influencerRepository = influencerRepository;
    }
    public List<Contract> findAllMyContract() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        return contractRepository.findAllByInfluencer(user.getId());
    }

    public List<Contract> findAllMyContractFromCampaign(long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        return contractRepository.findAllByBrandCampaign(userBrand.getId(), id);
    }

    public List<Contract> findAllMyContractFromInfluencer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        return contractRepository.findAllByInfluencer(user.getId());
    }

    public List<Contract> findAllMyContractFromCampaignAndStatus(long campaignId, long statusId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        return contractRepository.findAllByBrandCampaignStatus(userBrand.getId(), campaignId, statusId);
    }

    public String createContract(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Influencer influencer = influencerRepository.getById(user.getId());
        Campaign campaign = campaignRepository.getById(id);
        if (campaign.equals(null)){
            return "Campaign Not Exist";
        }
        if (campaign.getCampaignStatus().getId() != 1){
            return "Campaign Closed";
        }
        Brand brand = campaign.getBrand();
        ContractStatus contractStatus = contractStatusRepository.getById(1L);
        Contract contract = new Contract();
        contract.setInfluencer(influencer);
        contract.setBrand(brand);
        contract.setBudget(campaign.getBudget());
        contract.setContractStatus(contractStatus);
        contract.setCampaign(campaign);
        contractRepository.save(contract);
        return "Create Contract Success";
    }

    public String approveContract(ApproveContractRequest request, Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserBrand userBrand = userBrandRepository.findByUsername(name);
        Optional<Contract> check = contractRepository.findAllByBrand(userBrand.getId(), id);
        if (!check.isPresent()) {
            throw dataNotFound();
        }
        Contract temp = contractRepository.findByBrand(userBrand.getId(), id);
        if (temp.getContractStatus().getId() != 1){
            return "Contract has already approved";
        }
        LocalDate date = LocalDate.now();
        ContractFilePath contractFilePath = new ContractFilePath();
        contractFilePath.setId(id);
        contractFilePath.setFilePath("/images/contract/brandNote/" + date.toString() + temp.getCampaign().getId() + temp.getInfluencer().getFullname()+".png");
        contractFilePath.setDescription("Notes From Brand");
        Contract contract = new Contract();
        contract.setId(id);
        contract.setStartDate(date.toString());
        contract.setEndDate(request.getDeadline());
        contract.setBrand(temp.getBrand());
        contract.setBudget(temp.getBudget());
        contract.setCampaign(temp.getCampaign());
        contract.setInfluencer(temp.getInfluencer());
        ContractStatus contractStatus = contractStatusRepository.getById(2L);
        contract.setContractStatus(contractStatus);
        contractFilePath.setContract(contract);
        contractFilePathRepository.save(contractFilePath);
        contractRepository.save(contract);
        return "Contract Approved";
    }

    public String approveContractByAdmin(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserAdmin userAdmin = userAdminRepository.findByUsername(name);
        if (userAdmin.getUsername().isEmpty()) {
            return "You have no right to do this action";
        }
        Contract temp = contractRepository.findContractById(id);
        if (temp.getContractStatus().getId() != 2){
            return "Contract has already approved";
        }
        LocalDate date = LocalDate.now();
        Contract contract = new Contract();
        contract.setId(id);
        contract.setStartDate(temp.getStartDate());
        contract.setEndDate(temp.getEndDate());
        contract.setBrand(temp.getBrand());
        contract.setBudget(temp.getBudget());
        contract.setCampaign(temp.getCampaign());
        contract.setInfluencer(temp.getInfluencer());
        ContractStatus contractStatus = contractStatusRepository.getById(3L);
        contract.setContractStatus(contractStatus);
        contractRepository.save(contract);
        return "Contract Approved";
    }

    public String submitContractProve(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        User user = userRepository.findByUsername(name);
        Optional<Contract> check = contractRepository.findAllByInfluencerId(user.getId(), id);
        if (!check.isPresent()) {
            throw dataNotFound();
        }
        Contract temp = contractRepository.findByInfluencer(user.getId(), id);
        if (temp.getContractStatus().getId() != 3){
            return "Contract has already approved";
        }
        LocalDate date = LocalDate.now();
        ContractFilePath contractFilePath = new ContractFilePath();
        contractFilePath.setId(id);
        contractFilePath.setFilePath("/images/contract/influencerProve/" + date.toString() + temp.getCampaign().getId() + temp.getInfluencer().getFullname()+".png");
        contractFilePath.setDescription("Prove From Influencer");
        Contract contract = new Contract();
        contract.setId(id);
        contract.setStartDate(temp.getStartDate());
        contract.setEndDate(temp.getEndDate());
        contract.setBrand(temp.getBrand());
        contract.setBudget(temp.getBudget());
        contract.setCampaign(temp.getCampaign());
        contract.setInfluencer(temp.getInfluencer());
        ContractStatus contractStatus = contractStatusRepository.getById(4L);
        contract.setContractStatus(contractStatus);
        contractFilePath.setContract(contract);
        contractFilePathRepository.save(contractFilePath);
        contractRepository.save(contract);
        return "Contract Approved";
    }

    public String doneContractByAdmin(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        String name = authentication.getName();
        UserAdmin userAdmin = userAdminRepository.findByUsername(name);
        if (userAdmin.getUsername().isEmpty()) {
            return "You have no right to do this action";
        }
        Contract temp = contractRepository.findContractById(id);
        if (temp.getContractStatus().getId() != 4){
            return "Contract has already approved";
        }
        LocalDate date = LocalDate.now();
        Contract contract = new Contract();
        contract.setId(id);
        contract.setStartDate(temp.getStartDate());
        contract.setEndDate(temp.getEndDate());
        contract.setBrand(temp.getBrand());
        contract.setBudget(temp.getBudget());
        contract.setCampaign(temp.getCampaign());
        contract.setInfluencer(temp.getInfluencer());
        ContractStatus contractStatus = contractStatusRepository.getById(5L);
        contract.setContractStatus(contractStatus);
        contractRepository.save(contract);
        return "Contract Approved";
    }
}
