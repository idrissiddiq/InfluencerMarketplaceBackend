package com.InfluencerMarketpalce.serverside.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "tb_contract_file_path")
public class ContractFilePath {
    @Id
    @Column(name = "contract_file_path_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    public ContractFilePath(){

    }

    public ContractFilePath(Long id, String filePath, String description, Contract contract) {
        this.id = id;
        this.filePath = filePath;
        this.description = description;
        this.contract = contract;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
