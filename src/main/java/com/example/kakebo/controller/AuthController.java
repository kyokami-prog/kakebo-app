package com.example.kakebo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.kakebo.dto.LoginRequest;
import com.example.kakebo.dto.LoginResponse;
import com.example.kakebo.dto.RegisterRequest;
import com.example.kakebo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService service;

    public AuthController(AuthService service){
        this.service=service;
    }    
    //ユーザー登録
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest req){
        service.register(req);
    }

    //ログイン
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req){
        return service.login(req);
    }
    
    //名前取り出す
    @GetMapping("/me")
    public Object me(){
        String username=(String)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return java.util.Map.of("username",username);
    }
}
