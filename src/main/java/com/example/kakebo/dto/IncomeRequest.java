package com.example.kakebo.dto;

public record IncomeRequest (
    Integer amount,
    String category,
    String memo,
    String income_date
    
    
){}
