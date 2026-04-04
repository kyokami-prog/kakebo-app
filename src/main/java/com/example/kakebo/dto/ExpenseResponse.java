package com.example.kakebo.dto;

public record ExpenseResponse (
    Long id,
    Integer amount,
    String category,
    String memo,
    String expenseDate
){}
