package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
//Configuration = spring이 관리하는 영역이라고 선언하는 것
public class DaoFactory {

    //annotation으로 값을 주입하는 것
    //이걸 사용하려면 edit configuration에다가 값을 추가해줘야 사용가능
    @Value("${db.driver}")
    private String className;
    @Value("${db.username}")
    private String root;
    @Value("${db.password}")
    private String rootpw;
    @Value("${db.url}")
    private String url;

    @Bean
    public UserDao userDao() throws ClassNotFoundException {

        return new UserDao(dataSource());
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource =
                new SimpleDriverDataSource();
        // "com.mysql..." -> mysql 써야만 동작
        // -> runtimeonly(실행시 로딩이 필요한 라이브러리라는뜻)이기 때문에 클래스를 로드하는 부분이 필요
        // 그래야만 오라클,mysql 골라서 사용이 가능
        className = "com.mysql.cj.jdbc.Driver";
        root = "root";
        rootpw = "rootpw";
        url = "jdbc:mysql://localhost/user_db?" + "characterEncoding=utf-8&serverTimezone=UTC";
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        dataSource.setUsername(root);
        dataSource.setPassword(rootpw);
        dataSource.setUrl(url);

        return dataSource;
    }


}
