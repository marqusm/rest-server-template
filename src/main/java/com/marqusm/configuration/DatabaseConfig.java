package com.marqusm.configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Copyright 2017 (C) Bitner
 *
 * @author : Marko Mišković
 * @createdOn : 23-Sep-17
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

  private final static Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

  @Autowired
  private org.springframework.core.env.Environment env;

  @Autowired
  private JpaProperties jpaProperties;

  @Autowired
  private DataSource defaultDataSource;

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    return new HibernateJpaVendorAdapter();
  }

  @Bean
  public MultiTenantConnectionProvider multiTenantConnectionProvider() {
    return new AbstractMultiTenantConnectionProvider() {

      @Override
      protected ConnectionProvider getAnyConnectionProvider() {
        return new UserSuppliedConnectionProviderImpl() {
          @Override
          public Connection getConnection() throws SQLException {
            return defaultDataSource.getConnection();
          }

          @Override
          public void closeConnection(Connection conn) throws SQLException {
            conn.close();
          }

          @Override
          public boolean supportsAggressiveRelease() {
            return true;
          }

        };
      }

      @Override
      protected ConnectionProvider selectConnectionProvider(String tenantIdentifier) {
        return new UserSuppliedConnectionProviderImpl() {
          @Override
          public Connection getConnection() throws SQLException {
            Connection connection = defaultDataSource.getConnection();
            connection.setSchema(tenantIdentifier);
            return connection;
          }

          @Override
          public void closeConnection(Connection conn) throws SQLException {
            conn.close();
          }

          @Override
          public boolean supportsAggressiveRelease() {
            return true;
          }

        };
      }
    };
  }

  @Bean
  public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
    return new CurrentTenantIdentifierResolver() {
      @Override
      public String resolveCurrentTenantIdentifier() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // If session attribute exists returns tenantId saved on the session
        if (attr != null) {
          // Get tenant id from request
        }
        // Otherwise return default tenant
        logger.trace("Tenant resolved is: " + "public");
        return "public";
      }

      @Override
      public boolean validateExistingCurrentSessions() {
        return false;
      }
    };
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
      MultiTenantConnectionProvider multiTenantConnectionProvider,
      CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {
    Map<String, Object> properties = new HashMap<>();
    properties.putAll(jpaProperties.getHibernateProperties(dataSource));
    properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
    properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
    properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource);
    em.setPackagesToScan("com.marqusm");
    em.setJpaVendorAdapter(jpaVendorAdapter());
    em.setJpaPropertyMap(properties);
    return em;
  }

}
