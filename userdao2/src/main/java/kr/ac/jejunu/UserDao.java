package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
// final 로 지정한 애들에 대해 생성자 만들어주는 것
// 자동으로 생성자 만들어졌으니 의존성 주입은 자동으로 됨
// 이거 있으면 의존성 주입 되는 애구나 로 알면 될듯
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public User findById(Integer id){
        String sql = "select * from  userinfo where id = ?";
        Object[] params = new Object[]{id};
        return jdbcTemplate.query(sql,rs -> {
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        },id);
    }


    public void insert(User user){
        //데이터 어딨어? => mysql
        String sql = "insert into userinfo (name, password) values ( ?, ? )";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con ->{
            PreparedStatement preparedStatement = con.prepareStatement(

                    sql
                    , Statement.RETURN_GENERATED_KEYS
            );
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue()); // 키 값을 가져오는 것** 이거 하려고 keyHolder 쓰면서 복잡하게 한것
    }


    public void update(User user) {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql,params);
    }

    public void delete(Integer id){
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }




}






