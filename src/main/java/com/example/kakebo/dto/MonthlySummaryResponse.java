package com.example.kakebo.dto;

public record MonthlySummaryResponse (
    Integer year,
    Integer month,
    Integer totalExpense,
    Integer totalIncome,
    Integer balance
){
    
}
