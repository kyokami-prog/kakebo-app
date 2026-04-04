package com.example.kakebo.service;


import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.example.kakebo.dto.LoginRequest;
import com.example.kakebo.dto.LoginResponse;
import com.example.kakebo.dto.RegisterRequest;
import com.example.kakebo.repository.UserRepository;
import com.example.kakebo.util.JwtUtil;


@Service
public class AuthService {

    private final UserRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;
    
    public AuthService(UserRepository repo, JwtUtil jwtUtil, PasswordEncoder encoder){
        this.repo=repo;
        this.jwtUtil=jwtUtil;
        this.encoder=encoder;
        
        
    }
       //ユーザー登録
    public void register(RegisterRequest req){
        if(req ==null){
            throw new IllegalArgumentException("BODY_REQUIRED");
        }
    
        String username=req.username();
        String password=req.password();
        String hashedPassword= encoder.encode(password);
        
        repo.insert(username,hashedPassword);
    }

    //ログイン
    public LoginResponse login(LoginRequest req){
        if(req ==null){
            throw new IllegalArgumentException("BODY_REQUIRED");
        }
        String username=req.username();
        String hashedPassword=repo.findPasswordByUsername(username);

        
        if(hashedPassword == null || !encoder.matches(req.password(),hashedPassword)){
            throw new IllegalArgumentException("USERNAME_PASSWORD_ISNT_MATCH");
            
        }
        String token= jwtUtil.generateToken(username);
        return new LoginResponse(token);

    }
}

