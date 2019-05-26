package app.config;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence-oracle.properties")
public class HibernateConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException{
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("app.model");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() throws SQLException{
        OracleDataSource dataSource = new OracleDataSource();;

        dataSource.setDriverType(env.getProperty("jdbc.driver"));
        dataSource.setURL(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);

//        dataSource.setInitialPoolSize(env.getProperty("connection.pool.initialPoolSize", Integer.class));
//        dataSource.setMinPoolSize(env.getProperty("connection.pool.minPoolSize", Integer.class));
//        dataSource.setMaxPoolSize(env.getProperty("connection.pool.maxPoolSize", Integer.class));
//        dataSource.setMaxIdleTime(env.getProperty("connection.pool.maxIdleTime", Integer.class));

        return dataSource;

    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws SQLException{
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty(
                "hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty(
                "hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        return hibernateProperties;
    }

}
