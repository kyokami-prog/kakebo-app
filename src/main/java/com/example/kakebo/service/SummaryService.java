package com.example.kakebo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List; 
import com.example.kakebo.dto.SummaryResponse;
import com.example.kakebo.dto.MonthlySummaryResponse;
import com.example.kakebo.dto.MonthlyTrendResponse;
import com.example.kakebo.repository.ExpenseRepository;
import com.example.kakebo.repository.IncomeRepository;
import com.example.kakebo.repository.UserRepository;


@Service
public class SummaryService {
    
    private final ExpenseRepository expenseRepo;
    private final IncomeRepository incomeRepo;
    private final UserRepository userRepo;

    public SummaryService(ExpenseRepository expenseRepo, IncomeRepository incomeRepo, UserRepository userRepo){
        this.expenseRepo=expenseRepo;
        this.incomeRepo=incomeRepo;
        this.userRepo=userRepo;
    }

    private Long currentUserId(){
        String username =(String)SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

        return userRepo.findIdByUsername(username);
    }

    public SummaryResponse getSummary(){
        Long userId=currentUserId();

        Integer totalExpense=expenseRepo.sumAmountByUserId(userId);
        Integer totalIncome=incomeRepo.sumAmountByUserId(userId);
        Integer balance=totalIncome - totalExpense;

        return new SummaryResponse(totalExpense, totalIncome, balance);
    }
    //月の合計
    public MonthlySummaryResponse getMonthlySummary(Integer year, Integer month){
        Long userId = currentUserId();

        if(month<1 || month>12){
            throw new IllegalArgumentException("INVALID_MONTH");
        }
        Integer totalExpense = expenseRepo.sumMonthlyAmountByUserId(userId ,year, month);
        Integer totalIncome =incomeRepo.sumMonthlyAmountByUserId(userId, year, month);
        Integer balance = totalIncome - totalExpense;

        return new MonthlySummaryResponse(year, month, totalExpense,totalIncome, balance);
    }
    //年合計
    public List<MonthlyTrendResponse> getTrend(Integer year){
        Long userId= currentUserId();

        List<MonthlyTrendResponse> result= new ArrayList<>();

        for(int month=1; month<=12;month++){
            Integer totalExpense=expenseRepo.sumMonthlyAmountByUserId(userId, year, month);
            Integer totalIncome=incomeRepo.sumMonthlyAmountByUserId(userId, year, month);
            Integer balance= totalIncome-totalExpense;

            result.add(new MonthlyTrendResponse(
                    month,
                    totalExpense,
                    totalIncome,
                    balance
            ));

        }
        
        return result;
    }

    
}
