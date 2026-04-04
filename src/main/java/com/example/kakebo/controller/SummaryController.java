package com.example.kakebo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.kakebo.dto.SummaryResponse;
import com.example.kakebo.dto.MonthlySummaryResponse;
import com.example.kakebo.dto.MonthlyTrendResponse;
import com.example.kakebo.service.SummaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List; 




@RestController
@RequestMapping("/api/summary")
public class SummaryController {
    
    private final SummaryService service;

    public SummaryController(SummaryService service){
        this.service=service;
    }

    @GetMapping
    public SummaryResponse getSummary(){
        return service.getSummary();
    }

    @GetMapping("/monthly")
    public MonthlySummaryResponse getMonthlySummary(
            @RequestParam Integer year,
            @RequestParam Integer month
    ){
        return service.getMonthlySummary(year,month);
    }
    
    @GetMapping("/trend")
    public List<MonthlyTrendResponse> getTrend(@RequestParam Integer year){
        return service.getTrend(year);
    
    }
    
    
}
