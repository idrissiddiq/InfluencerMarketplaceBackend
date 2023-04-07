package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Contract;
import com.InfluencerMarketpalce.serverside.model.request.ApproveContractRequest;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/me")
    public ResponseListData<Contract> findAllMyContractFromInfluencer() {
        return new ResponseListData(contractService.findAllMyContractFromInfluencer());
    }

    @PostMapping("/{id}")
    public String createContract(@PathVariable Long id) {
        return contractService.createContract(id);
    }

    @PutMapping("/admin/{id}")
    public String approveContractByAdmin(@PathVariable Long id){
        return contractService.approveContractByAdmin(id);
    }

    @PutMapping("/influencer/{id}")
    public String submitContractProve(@PathVariable Long id){
        return contractService.submitContractProve(id);
    }

    @PutMapping("/admin/done/{id}")
    public String doneContractByAdmin(@PathVariable Long id){
        return contractService.doneContractByAdmin(id);
    }
}
