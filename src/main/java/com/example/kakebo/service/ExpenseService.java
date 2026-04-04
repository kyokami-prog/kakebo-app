package com.example.kakebo.service;



import org.springframework.security.core.context.SecurityContextHolder;
import com.example.kakebo.dto.ExpenseRequest;
import com.example.kakebo.dto.ExpenseResponse;
import com.example.kakebo.repository.ExpenseRepository;
import com.example.kakebo.repository.UserRepository;
import com.example.kakebo.exception.ForbiddenException;


import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    
    private final ExpenseRepository repo;
    private final UserRepository userRepo;

    public ExpenseService(ExpenseRepository repo, UserRepository userRepo){
        this.repo=repo;
        this.userRepo=userRepo;
    }

    private Long currentUserId(){
        String username=(String)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepo.findIdByUsername(username);
    }
//登録
    public void register(ExpenseRequest req){
        if(req==null){
            throw new IllegalArgumentException("BODY_REQUIRED");  
        }

        Integer amount= req.amount();
        String category=req.category();
        String memo=req.memo();
        String expenseDate=req.expenseDate();

        repo.insert(currentUserId(),amount,category,memo,expenseDate);
    }
//一覧取得
    public java.util.List<ExpenseResponse> list(){
        return repo.list(currentUserId());
    }
//削除
    public void delete(Long id){

        Long loginUserId=currentUserId();
        Long ownerUserId=repo.findUserIdById(id);
        
        if(!loginUserId.equals(ownerUserId)){
            throw new ForbiddenException("FORBIDDEN");
        }
        repo.delete(id,loginUserId);
    }
//更新
    public ExpenseResponse update(Long id, ExpenseRequest req){
      

       if(req==null){
           throw new IllegalArgumentException("BODY_REAUIRED");  
       }

       Integer amount= req.amount();
       String category=req.category();
       String memo=req.memo();
       String expenseDate=req.expenseDate();

      return repo.update(id,currentUserId(),amount,category,memo,expenseDate);
    }

}
