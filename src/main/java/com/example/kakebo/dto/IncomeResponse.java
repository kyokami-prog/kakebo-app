package com.example.kakebo.dto;

public record IncomeResponse (
    Long id,
    Integer amount,
    String category,
    String memo,
    String income_date

){}
    


