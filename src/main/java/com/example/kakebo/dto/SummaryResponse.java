package com.example.kakebo.dto;

public record SummaryResponse (
    Integer totalExpense,
    Integer totalIncome,
    Integer balance
){}
