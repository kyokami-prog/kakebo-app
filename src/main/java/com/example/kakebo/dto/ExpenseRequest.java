package com.example.kakebo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ExpenseRequest (

    @NotNull(message="AMOUNT_REQUIRED")
    @Positive(message="INVALID_AMOUNT")
    Integer amount,

    @NotBlank(message="CATEGORY_REQUIRED")
    String category,

    String memo,

    @NotBlank(message="EXPENSE_DATE_REQUIRED")
    String expenseDate
){}

