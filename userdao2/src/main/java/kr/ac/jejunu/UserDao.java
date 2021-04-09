package kr.ac.jejunu;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.security.Key;
import java.sql.*;

public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Integer id) throws SQLException {
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

    //    User jdbcContextForFindById(StatementStrategy statementStrategy) throws SQLException {
//        return jdbcContext.jdbcContextForFindById(statementStrategy);
//    }

    public void insert(User user) throws SQLException {
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

//    void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
//        jdbcContext.jdbcContextForInsert(user, statementStrategy);
//    }


    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql,params);
    }

    public void delete(Integer id) throws SQLException {
        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }

    //    void jdbcContextUpdate(StatementStrategy statementStrategy) throws SQLException {
//        jdbcContext.jdbcContextUpdate(statementStrategy);
//    }

//    abstract private PreparedStatement makeStatement(Integer id, Connection connection) throws SQLException;
//        PreparedStatement preparedStatement = null;
//        preparedStatement = connection.prepareStatement(
//                "delete from userinfo where id = ?"
//        );
//        //UserDaoTests 에서 userDao.delete(user.getId()); 이므로 setInt(1,id)
//        preparedStatement.setInt(1, id);
//        return preparedStatement;
//    }


}






