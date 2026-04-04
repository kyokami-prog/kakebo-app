package com.example.kakebo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.kakebo.dto.IncomeResponse;
import com.example.kakebo.dto.IncomeRequest;

import com.example.kakebo.service.IncomeService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    
    private final IncomeService service;

    public IncomeController(IncomeService service){
        this.service= service;
    }

    //登録
    @PostMapping
    public void register(@RequestBody @Valid IncomeRequest req){
        service.register(req);
    }
    //一覧取得
    @GetMapping
    public java.util.List<IncomeResponse> list(){
        return service.list();
    }
    //削除
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
    //更新
    @PutMapping("/{id}")
    public IncomeResponse update(@PathVariable Long id, @RequestBody @Valid IncomeRequest req){
        return service.update(id,req);

    }
}
