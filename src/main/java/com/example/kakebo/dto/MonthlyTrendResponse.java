package com.example.kakebo.dto;

public record MonthlyTrendResponse (
    Integer month,
    Integer totalExpense,
    Integer totalIncome,
    Integer balance
){}
