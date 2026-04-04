package com.example.kakebo.repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.kakebo.dto.ExpenseResponse;

@Repository
public class ExpenseRepository {
    
    private final JdbcTemplate jdbc;

    public ExpenseRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
       
    
    }

    //登録
    public void insert(Long userId ,Integer amount, String category, String memo, String expenseDate){
        String sql="INSERT INTO expenses (user_id,amount,category,memo,expense_date) VALUES(?,?,?,?,?)";
            jdbc.update(sql,userId,amount,category,memo,expenseDate);
 
    }
    //一覧取得
    public List<ExpenseResponse> list(Long userId){
        String sql="SELECT id, amount, category, memo, expense_date FROM expenses WHERE user_id=?";
        
        return jdbc.query(sql,(rs,i)-> new ExpenseResponse(
                rs.getLong("id"),
                rs.getInt("amount"),
                rs.getString ("category"),
                rs.getString("memo"),
                rs.getString("expense_date")
        ),userId);

    }
    //削除
    public void delete(Long id,Long userId){
        String sql ="DELETE FROM expenses WHERE id=? AND user_id=?";
        jdbc.update(sql,id,userId);
    }
    //更新
    public ExpenseResponse update(Long id , Long userId, Integer amount, String category, String memo, String expenseDate){
        String sql="UPDATE expenses SET amount=?, category=?, memo=?, expense_date=? WHERE id=? AND user_id=?";
        jdbc.update(sql,amount,category,memo,expenseDate, id,userId);
        return findById(id,userId);
    }
    
    public ExpenseResponse findById(Long id, Long userId){
        String sql="SELECT id, amount, category, memo, expense_date FROM expenses WHERE id=? AND user_id=?";

        return jdbc.queryForObject(sql,(rs, i) -> new ExpenseResponse(
                rs.getLong("id"),
                rs.getInt("amount"),
                rs.getString("category"),
                rs.getString("memo"),
                rs.getString ("expense_date")),id,userId
        );
    }
    //合計
    public Integer sumAmountByUserId(Long userId){
        String sql="SELECT COALESCE(SUM(amount),0) FROM expenses WHERE user_id=?";
        return jdbc.queryForObject(sql,Integer.class,userId);
    }

    //月別合計
    public Integer sumMonthlyAmountByUserId(Long userId, Integer year, Integer month){
        String sql="""
                SELECT COALESCE(SUM(amount),0)
                FROM expenses
                WHERE user_id=?
                    AND YEAR(expense_date)=?
                    AND MONTH(expense_date)=?
                """;
        
        return jdbc.queryForObject(sql, Integer.class, userId, year, month);
    }
    //userId取得
    public Long findUserIdById(Long id){
        String sql="SELECT user_id FROM expenses where id=?";
        return jdbc.queryForObject(sql,Long.class,id);
    }
}
