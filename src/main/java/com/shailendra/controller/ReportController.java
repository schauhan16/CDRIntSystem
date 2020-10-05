package com.shailendra.controller;

import com.shailendra.pojo.Report;
import com.shailendra.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("callRecord")
    public Report getCallRecordReport(){
        return reportingService.getDefaultReport();
    }
}
