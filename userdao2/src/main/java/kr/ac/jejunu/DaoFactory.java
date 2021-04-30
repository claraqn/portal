package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

//Configuration = spring이 관리하는 영역이라고 선언하는 것
@Configuration
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
    public JdbcTemplate jdbcTemplate() throws ClassNotFoundException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource =
                new SimpleDriverDataSource();

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
