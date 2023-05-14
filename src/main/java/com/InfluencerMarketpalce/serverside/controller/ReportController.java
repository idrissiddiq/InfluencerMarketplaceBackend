package com.InfluencerMarketpalce.serverside.controller;

import com.InfluencerMarketpalce.serverside.model.Report;
import com.InfluencerMarketpalce.serverside.model.response.ResponseListData;
import com.InfluencerMarketpalce.serverside.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PutMapping("/monthlyReport")
    public String MonthlyReport(@RequestBody Map<String, Object> param){
        return reportService.MonthlyReport(param);
    }

    @PostMapping("/createReport")
    public String createReport(@RequestBody Map<String, Object> param){
        return reportService.createReport(param);
    }

    @GetMapping("/findAllMonthlyReport")
    public List<Report> findAllMonthlyReport(){
        return reportService.findAllMonthlyReport();
    }

    @GetMapping("/findAllReport/{id}")
    public ResponseListData<Report> findAllReportByBrand(@PathVariable Long id){
        return new ResponseListData(reportService.findAllReportByBrand(id));
    }
}
