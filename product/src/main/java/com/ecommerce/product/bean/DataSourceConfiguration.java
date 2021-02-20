package com.ecommerce.product.bean;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class DataSourceConfiguration {

    private static final String MODELS_PACKAGE = "com.ecommerce.product.model";
    private static final String JDBC_BASE_URL = "jdbc:mysql://";
    private static final String URL_DELIMITER = "/";
    private static final String MYSQL_HOST = "localhost";
    private static final String MYSQL_DATABASE = "product_catalog";

    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final boolean SHOW_SQL = true;
    private static final boolean FORMAT_SQL = false;
    private static final int BATCH_SIZE = 50;
    private static final boolean ORDER_INSERTS = true;
    private static final boolean ORDER_UPDATES = true;
    private static final String HBM_2_DDL_AUTO = "none";
    private static final boolean GENERATE_STATISTICS = false;


    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(MODELS_PACKAGE);
        sessionFactory.setHibernateProperties(createHibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(DataSourceProperties.DRIVER_CLASS);
        dataSource.setJdbcUrl(JDBC_BASE_URL + MYSQL_HOST + URL_DELIMITER + MYSQL_DATABASE);
        dataSource.setUser("root");
        dataSource.setPassword("rootpassword");
        dataSource.setMaxIdleTime(DataSourceProperties.MAX_IDLE_TIME);
        dataSource.setMaxIdleTimeExcessConnections(DataSourceProperties.MAX_IDLE_TIME_EXCESS_CONNECTIONS);
        dataSource.setInitialPoolSize(DataSourceProperties.INITIAL_POOL_SIZE);
        dataSource.setMinPoolSize(DataSourceProperties.MIN_POOL_SIZE);
        dataSource.setMaxPoolSize(DataSourceProperties.MAX_POOL_SIZE);
        dataSource.setNumHelperThreads(DataSourceProperties.NUM_HELPER_THREADS);
        dataSource.setUnreturnedConnectionTimeout(DataSourceProperties.UNRETURNED_CONNECTION_TIMEOUT);
        return dataSource;
    }

    private static Properties createHibernateProperties() {
        final Properties properties = new Properties();
        properties.put(HibernateProperties.DIALECT_KEY, DIALECT);
        properties.put(HibernateProperties.SHOW_SQL_KEY, SHOW_SQL);
        properties.put(HibernateProperties.FORMAT_SQL_KEY, FORMAT_SQL);
        properties.put(HibernateProperties.JDBC_BATCH_SIZE_KEY, BATCH_SIZE);
        properties.put(HibernateProperties.ORDER_INSERTS_KEY, ORDER_INSERTS);
        properties.put(HibernateProperties.ORDER_UPDATES_KEY, ORDER_UPDATES);
        properties.put(HibernateProperties.HBM_2_DDL_AUTO_KEY, HBM_2_DDL_AUTO);
        properties.put(HibernateProperties.GENERATE_STATISTICS_KEY, GENERATE_STATISTICS);
        return properties;
    }

    private static final class HibernateProperties {
        private static final String DIALECT_KEY = "hibernate.dialect";
        private static final String SHOW_SQL_KEY = "hibernate.show_sql";
        private static final String FORMAT_SQL_KEY = "hibernate.format_sql";
        private static final String JDBC_BATCH_SIZE_KEY = "hibernate.jdbc.batch_size";
        private static final String ORDER_INSERTS_KEY = "hibernate.order_inserts";
        private static final String ORDER_UPDATES_KEY = "hibernate.order_updates";
        private static final String HBM_2_DDL_AUTO_KEY = "hibernate.hbm2ddl.auto";
        private static final String GENERATE_STATISTICS_KEY = "hibernate.generate_statistics";
//        private static final String AUTO_COMMIT = "hibernate.connection.autocommit";
    }

    private static final class DataSourceProperties {
        private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

        private static final int UNRETURNED_CONNECTION_TIMEOUT = 180;
        private static final int MAX_IDLE_TIME = 180;
        private static final int MAX_IDLE_TIME_EXCESS_CONNECTIONS = 120;
        private static final int MIN_POOL_SIZE = 5;
        private static final int MAX_POOL_SIZE = 20;
        private static final int INITIAL_POOL_SIZE = 5;
        private static final int NUM_HELPER_THREADS = 5;
    }

}
