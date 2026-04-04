package com.example.kakebo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.kakebo.dto.IncomeResponse;


@Repository
public class IncomeRepository {
    
    private final JdbcTemplate jdbc;

    public IncomeRepository(JdbcTemplate jdbc){
        this.jdbc=jdbc;
    }
    //登録
    public void insert(Long userId,Integer amount,String category,String memo,String income_date){
        String sql="INSERT INTO incomes (user_id,amount,category,memo,income_date)VALUES(?,?,?,?,?)";
        jdbc.update(sql,userId,amount,category,memo,income_date);
    }
    //一覧取得
    public List<IncomeResponse> list(Long userId){
        String sql="SELECT id,amount,category,memo,income_date FROM incomes WHERE user_id=?";
        return jdbc.query(sql,(rs,i)-> new IncomeResponse(
            rs.getLong("id"),
            rs.getInt("amount"),
            rs.getString("category"),
            rs.getString("memo"),
            rs.getString("income_date")

        ),userId);
    }
    //削除
    public void delete(Long id,Long userId){
        String sql="DELETE FROM incomes WHERE id=? AND user_id=?";
        jdbc.update(sql,id,userId);
    }

    //更新
    public IncomeResponse update(Long id, Long userId, Integer amount, String category, String memo, String income_date){
        String sql="UPDATE incomes SET amount=?,category=?,memo=?,income_date=? WHERE id =? AND user_id=?";
        jdbc.update(sql,amount,category,memo,income_date,id,userId);
        return findById(id,userId);
    }

    public IncomeResponse findById(Long id,Long userId){
        String sql="SELECT id, amount ,category,memo,income_date FROM incomes WHERE id=? AND user_id=?";
        return jdbc.queryForObject(sql,(rs,i)-> new IncomeResponse(
            rs.getLong("id"),
            rs.getInt("amount"),
            rs.getString("category"),
            rs.getString("memo"),
            rs.getString("income_date")
        ),id,userId);
    }

    //合計
    public Integer sumAmountByUserId(Long userId){
        String sql="SELECT COALESCE(SUM(amount),0) FROM incomes WHERE user_id=?";
        return jdbc.queryForObject(sql,Integer.class,userId);
    }
    //合計
        public Integer sumMonthlyAmountByUserId(Long userId, Integer year, Integer month){
        String sql="""
                SELECT COALESCE(SUM(amount),0) 
                FROM incomes 
                WHERE user_id=?
                    AND YEAR(income_date)=?
                    AND MONTH(income_date)=?
                    """;
                            
                            

        return jdbc.queryForObject(sql,Integer.class,userId,year, month);
    }
    //userId取得
    public Long findUserIdById(Long id){
        String sql="SELECT user_id FROM incomes where id=?";
        return jdbc.queryForObject(sql,Long.class,id);
    }


}
