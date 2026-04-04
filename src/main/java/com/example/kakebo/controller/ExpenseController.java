package com.example.kakebo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.kakebo.dto.ExpenseResponse;
import com.example.kakebo.dto.ExpenseRequest;

import com.example.kakebo.service.ExpenseService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    
    private final ExpenseService service;

    public ExpenseController(ExpenseService service){
        this.service=service;
    }    
    //登録
    @PostMapping
    public void register(@RequestBody @Valid ExpenseRequest req){
        service.register(req);
    }

    //一覧取得
    @GetMapping
    public java.util.List<ExpenseResponse> list(){
        return service.list();
    }
    
    //削除
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    //更新
    @PutMapping("/{id}")
    public ExpenseResponse update(@PathVariable Long id, @RequestBody @Valid ExpenseRequest req){
        return service.update(id,req);
    }
}

