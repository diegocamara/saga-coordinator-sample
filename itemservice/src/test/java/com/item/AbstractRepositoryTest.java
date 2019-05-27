package com.item;

import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class AbstractRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    public  EntityManager entityManager;

    @BeforeEach
    public void beforeEach() throws IOException {
        entityManagerFactory = sessionFactory();
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void afterEach() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void transaction(Runnable command) {
        entityManager.getTransaction().begin();
        command.run();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }


    private SessionFactory sessionFactory() throws IOException {
        return localSessionFactoryBean().getObject();
    }

    private LocalSessionFactoryBean localSessionFactoryBean() throws IOException {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setHibernateProperties(properties());

        Class<?>[] annotatedClasses = annotatedClasses();

        String packagesToScan = packagesToScan();

        if (annotatedClasses != null && annotatedClasses.length > 0) {
            localSessionFactoryBean.setAnnotatedClasses(annotatedClasses);
        } else if (packagesToScan != null && !packagesToScan.isEmpty()) {
            localSessionFactoryBean.setPackagesToScan(packagesToScan);
        }


        localSessionFactoryBean.afterPropertiesSet();
        return localSessionFactoryBean;
    }

    private DataSource dataSource() {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setUrl("jdbc:h2:mem:default;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        jdbcDataSource.setUser("sa");
        jdbcDataSource.setPassword("");
        return jdbcDataSource;
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");
        properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
        properties.put(AvailableSettings.SHOW_SQL, "true");
        return properties;
    }

    Class<?>[] annotatedClasses(){
        return null;
    }

    String packagesToScan(){
        return "";
    }

}
