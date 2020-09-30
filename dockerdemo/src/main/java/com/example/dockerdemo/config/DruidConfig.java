package com.example.dockerdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
@SuppressWarnings("unchecked")
public class DruidConfig {
    private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    /**
     * spring.datasource.url
     * */
    @Value("${spring.datasource.url}")
    private String dbUrl;

    /**
     * spring.datasource.username
     * */
    @Value("${spring.datasource.username}")
    private String username;

    /**
     * spring.datasource.password
     * */
    @Value("${spring.datasource.password}")
    private String password;

    /**
     * spring.datasource.driver-class-name
     * */
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * spring.datasource.login-user-name
     * */
    @Value("${spring.datasource.login-user-name}")
    private String loginUserName;

    /**
     * spring.datasource.login-password
     * */
    @Value("${spring.datasource.login-password}")
    private String loginPassword;

    /**
     * spring.datasource.initial-size
     * */
    @Value("${spring.datasource.initial-size}")
    private int initialSize;

    /**
     * spring.datasource.datasource.min-idle
     * */
    @Value("${spring.datasource.min-idle}")
    private int minIdle;

    /**
     * spring.datasource.max-active
     * */
    @Value("${spring.datasource.max-active}")
    private int maxActive;

    /**
     * spring.datasource.max-wait
     * */
    @Value("${spring.datasource.max-wait}")
    private int maxWait;

    /**
     * spring.datasource.time-between-eviction-runs-millis
     * */
    @Value("${spring.datasource.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;

    /**
     * spring.datasource.min-evictable-idle-time-millis
     * */
    @Value("${spring.datasource.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    /**
     * spring.datasource.validation-query
     * */
    @Value("${spring.datasource.validation-query}")
    private String validationQuery;

    /**
     * spring.datasource.test-while-idle
     * */
    @Value("${spring.datasource.test-while-idle}")
    private boolean testWhileIdle;

    /**
     * spring.datasource.test-on-borrow
     * */
    @Value("${spring.datasource.test-on-borrow}")
    private boolean testOnBorrow;

    /**
     * spring.datasource.test-on-return
     * */
    @Value("${spring.datasource.test-on-return}")
    private boolean testOnReturn;

    /**
     * spring.datasource.filters
     * */
    @Value("${spring.datasource.filters}")
    private String filters;


    /**
     * spring.datasource.connection-properties
     * */
    @Value("${spring.datasource.connection-properties}")
    private String connectionProperties;


    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        // 设置白名单
        reg.addInitParameter("allow", "");
        // 设置黑名单
        reg.addInitParameter("deny", "");

        // 设置登录查看信息的账号密码
        reg.addInitParameter("loginUsername", loginUserName);
        reg.addInitParameter("loginPassword", loginPassword);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean("dataSource")
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        // connectionProperties 属性写入方式1
        Properties properties = new Properties();
        String[] cps = connectionProperties.split(";");
        for (String connectionPropertie : cps) {
            String[] attr = connectionPropertie.split("=");
            properties.put(attr[0].trim(), attr[1].trim());
        }
        datasource.setConnectProperties(properties);
        // connectionProperties 属性写入方式2
        // datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }


    /**
     * 配置事务管理 当事务类型为 {@link org.springframework.transaction.annotation.Propagation#NESTED} 时需要修改为 {@link DataSourceTransactionManager}
     * */
    @Bean("transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
