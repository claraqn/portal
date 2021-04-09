package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {

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

        return new UserDao(jdbcTemplate());
    }

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
