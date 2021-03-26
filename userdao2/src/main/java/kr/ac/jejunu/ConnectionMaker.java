package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException; //{ //더 상위레벨로 추상화하는것
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost/user_db?" +
//                        "characterEncoding=utf-8&serverTimezone=UTC"
//                , "root", "rootpw"
//        );
//    }
}