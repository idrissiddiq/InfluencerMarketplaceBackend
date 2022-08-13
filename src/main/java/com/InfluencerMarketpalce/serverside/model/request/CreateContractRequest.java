package com.InfluencerMarketpalce.serverside.model.request;

public class CreateContractRequest {
    private Long budget;

    public CreateContractRequest(){

    }

    public CreateContractRequest(Long budget) {
        this.budget = budget;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }
}
