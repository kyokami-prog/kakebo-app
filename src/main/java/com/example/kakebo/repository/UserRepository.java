package com.example.kakebo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository{

    private final JdbcTemplate jdbc;
    public UserRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }
        //ユーザー登録
        public void insert(String username,String password){
            String sql="INSERT INTO users (username,password) VALUES(?,?)";
            jdbc.update(sql, username, password);
        }

        //ユーザーネームからパスワードを見つける
        public String findPasswordByUsername(String username){
            String sql="SELECT password FROM users WHERE username=?";
            return jdbc.queryForObject(sql,String.class,username);
        }
        
        //user_idを取得
        public Long findIdByUsername(String username){
            String sql="SELECT id FROM users WHERE username=?";
            return jdbc.queryForObject(sql,Long.class, username);
        }

    
}