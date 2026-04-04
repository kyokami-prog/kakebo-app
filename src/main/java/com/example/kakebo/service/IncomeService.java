package com.example.kakebo.service;

import java.util.List;

import com.example.kakebo.dto.IncomeRequest;
import com.example.kakebo.dto.IncomeResponse;
import com.example.kakebo.repository.IncomeRepository;
import com.example.kakebo.repository.UserRepository;
import com.example.kakebo.exception.ForbiddenException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {

    private final IncomeRepository repo;
    private final UserRepository userRepo;

    public IncomeService(IncomeRepository repo, UserRepository userRepo){
        this.repo=repo;
        this.userRepo=userRepo;
    }
    
    //ユーザーid取得
    private Long currentUserId(){
        String username=(String)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepo.findIdByUsername(username);        
    }


//登録
    public void register(IncomeRequest req){
        if(req==null){
            throw new IllegalArgumentException("BODY_REQUIRED");

        }
        Integer amount=req.amount();
        String category=req.category();
        String memo=req.memo();
        String income_date=req.income_date();

        repo.insert(currentUserId(),amount,category,memo,income_date);
    }
//一覧取得
    public List<IncomeResponse> list(){
        return repo.list(currentUserId());
    }
//削除
    public void delete(Long id){
        Long loginUserId = currentUserId();
        Long ownerUserId=repo.findUserIdById(id);

        if(!loginUserId.equals(ownerUserId)){
            throw new ForbiddenException("FORBIDDEN");
        }

        repo.delete(id,loginUserId);
    }

//更新
    public IncomeResponse update(Long id, IncomeRequest req){
        
        if(req==null){
            throw new IllegalArgumentException("BODY_REQUIRED");
        }
        Integer amount=req.amount();
        String category=req.category();
        String memo=req.memo();
        String income_date=req.income_date();

        return repo.update(id,currentUserId(),amount,category,memo,income_date);
    }
}