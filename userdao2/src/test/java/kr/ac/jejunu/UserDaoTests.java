package kr.ac.jejunu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    static UserDao userDao; // **static 지정 = 영역 메모리 사용

    @BeforeAll
    public static void setup() throws ClassNotFoundException {
//        ClassPathXmlApplicationContext applicationContext =
//                new ClassPathXmlApplicationContext("daoFactory.xml");
//        StaticApplicationContext applicationContext =
//                new StaticApplicationContext();
//        BeanDefinition dataBeanDefinition = new RootBeanDefinition(SimpleDriverDataSource.class);
//        dataBeanDefinition.getPropertyValues()
//                .addPropertyValue("driverClass"
//                        , Class.forName(System.getenv("DB_DRIVER")));
//        dataBeanDefinition.getPropertyValues()
//                .addPropertyValue("username"
//                        , System.getenv("DB_USERNAME"));
//        dataBeanDefinition.getPropertyValues()
//                .addPropertyValue("password"
//                        , System.getenv("DB_PASSWORD"));
//        dataBeanDefinition.getPropertyValues()
//                .addPropertyValue("url"
//                        , System.getenv("DB_URL"));
//
//
//
//        applicationContext.registerBeanDefinition("dataSource", dataBeanDefinition);
//        BeanDefinition jdbcBeanDefinition = new RootBeanDefinition(JdbcTemplate.class);
//        jdbcBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(
//                new RuntimeBeanReference("dataSource")
//        );
//        applicationContext.registerBeanDefinition("jdbcTemplate", jdbcBeanDefinition);
//        BeanDefinition userDaoBeanDefinition = new RootBeanDefinition(UserDao.class);
//        userDaoBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(
//                new RuntimeBeanReference("jdbcTemplate")
//        );
//        applicationContext.registerBeanDefinition("userDao", userDaoBeanDefinition);
//        ApplicationContext applicationContext
//                = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("kr.ac.jejunu");
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Integer id = 1;
        String name = "clara";
        String password = "1234";

//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "clara";
        String password = "1234";


        User user = new User();
        user.setName(name);
        user.setPassword(password);
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao = daoFactory.getUserDao();
        userDao.insert(user);

        User insertedUser = userDao.findById(user.getId()); // insert된 user 나올것
        assertThat(user.getId(), greaterThan(0));
        assertThat(insertedUser.getId(), is(user.getId())); // id값은 0보다 클것이라고 예상하는것
        assertThat(insertedUser.getName(), is(user.getName())); //assertThat함수->값이 같으면 성공 다르면 실패 반환
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void update() throws SQLException {
        String name = "clara";
        String password = "1234";


        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        user.setName("xiajunsu");
        user.setPassword("1111");

        userDao.update(user);

        User updatedUser = userDao.findById(user.getId());
        assertThat(updatedUser.getId(), is(user.getId()));
        assertThat(updatedUser.getName(), is(user.getName()));
        assertThat(updatedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void delete() throws SQLException {
        String name = "xiajunsu";
        String password = "1111";


        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());
        assertThat(deletedUser, nullValue());

    }

//
//    @Test
//    public void getHalla() throws SQLException, ClassNotFoundException {
//        Integer id = 1;
//        String name = "clara";
//        String password = "1234";
//
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        User user = userDao.findById(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//
//    @Test
//    public void insertHalla() throws SQLException, ClassNotFoundException {
//        String name = "clara";
//        String password = "1234";
//
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
//        userDao.insert(user);
//
//        User insertedUser = userDao.findById(user.getId()); // insert된 user 나올것
//        assertThat(user.getId(), greaterThan(0));
//        assertThat(insertedUser.getId(), is(user.getId())); // id값은 0보다 클것이라고 예상하는것
//        assertThat(insertedUser.getName(), is(user.getName()));
//        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//    }
}
