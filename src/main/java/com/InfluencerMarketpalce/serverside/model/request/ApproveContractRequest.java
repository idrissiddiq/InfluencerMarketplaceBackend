package com.InfluencerMarketpalce.serverside.model.request;

public class ApproveContractRequest {
    private String deadline;

    public ApproveContractRequest(){

    }

    public ApproveContractRequest(String deadline) {
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
