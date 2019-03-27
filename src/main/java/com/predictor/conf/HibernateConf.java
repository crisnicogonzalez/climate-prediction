package com.predictor.conf;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(ignoreResourceNotFound=true,value="classpath:env/app.properties")
public class HibernateConf {


    @Value("${meli.predictor.sql.url}")
    private String url;
    @Value("${meli.predictor.sql.username}")
    private String userName;
    @Value("${meli.predictor.sql.pass}")
    private String pass;
    @Value("${meli.predictor.sql.driver.class.name}")
    private String driverClassName;

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        final String[] packagesToScan = new String[]{"com.predictor.dao.entity"};
        sessionFactory.setPackagesToScan(packagesToScan);
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setDataSource(this.dataSource());
        return sessionFactory;
    }


    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(pass);
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        hibernateProperties.setProperty("hibernate.show_sql", "false");
        hibernateProperties.setProperty("hibernate.format_sql", "false");
        hibernateProperties.setProperty("hibernate.generate_statistics", "false");
        hibernateProperties.setProperty("hibernate.connection.autocommit", "false");
        hibernateProperties.setProperty("hibernate.connection.zeroDateTimeBehavior", "convertToNull");
        hibernateProperties.setProperty("hibernate.connection.transformedBitIsBoolean", "true");
        hibernateProperties.setProperty("hibernate.flushMode", "ALWAYS");
        hibernateProperties.setProperty("hibernate.jdbc.batch_size", "800");
        return hibernateProperties;
    }
}
