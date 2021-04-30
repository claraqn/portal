package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcContext {
    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    User jdbcContextForFindById(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            //데이터 어딨어? => mysql
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            try {
                resultSet.close();
            } catch (Exception throwables) { //  모든 예외를 받아줘야함
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //데이터 어딨어? => mysql
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
    }

    void jdbcContextUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //데이터 어딨어? => mysql
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);

            preparedStatement.executeUpdate();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
    }

    void update(String sql, Object[] params) throws SQLException {
        jdbcContextUpdate( connection -> {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            //UserDaoTests 에서 userDao.delete(user.getId()); 이므로 setInt(1,id)
//            preparedStatement.setInt(1, id);
            return preparedStatement;
        });
    }

    void insert(User user, String sql, Object[] params, UserDao userDao) throws SQLException {
        jdbcContextForInsert(user, connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(

                    sql
                    , Statement.RETURN_GENERATED_KEYS
            );
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        });
    }

    User findById(String sql, Object[] params) throws SQLException {
        return jdbcContextForFindById(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< params.length; i++){
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        });
    }
}