package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Contract;
import com.InfluencerMarketpalce.serverside.model.request.ApproveContractRequest;
import com.InfluencerMarketpalce.serverside.model.request.CreateContractRequest;
import com.InfluencerMarketpalce.serverside.model.request.UpdateCampaignRequest;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/contract")
public class ContractController {
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public ResponseListData<Contract> findAllMyContract() {
        return new ResponseListData(contractService.findAllMyContract());
    }

    @GetMapping("/{id}")
    public ResponseListData<Contract> findAllMyContractFromCampaign(@PathVariable Long id) {
        return new ResponseListData(contractService.findAllMyContractFromCampaign(id));
    }

    @PostMapping("/{id}")
    public String createContract(@RequestBody CreateContractRequest request, @PathVariable Long id) {
        return contractService.createContract(request, id);
    }

    @PutMapping("/{id}")
    public String approveContract(@RequestBody ApproveContractRequest request, @PathVariable Long id){
        return contractService.approveContract(request, id);
    }
}
