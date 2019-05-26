package com.item;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class AbstractRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;
    private static String packagesToScan = "com.item.entity";

    @BeforeAll
    public static void beforeAll() throws IOException {
        SessionFactory sessionFactory = sessionFactory();
        entityManagerFactory = sessionFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void afterAll() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void transaction(Runnable command) {
        entityManager.getTransaction().begin();
        command.run();
        entityManager.flush();
        entityManager.getTransaction().commit();

    }


    private static SessionFactory sessionFactory() throws IOException {
        return localSessionFactoryBean().getObject();
    }

    private static LocalSessionFactoryBean localSessionFactoryBean() throws IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setHibernateProperties(properties());
        localSessionFactoryBean.setPackagesToScan(packagesToScan);
        localSessionFactoryBean.afterPropertiesSet();
        return localSessionFactoryBean;
    }

    private static DataSource dataSource() {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setUrl("jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("");
        return jdbcDataSource;
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");
        properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
        properties.put(AvailableSettings.SHOW_SQL, "true");
        return properties;
    }

}
