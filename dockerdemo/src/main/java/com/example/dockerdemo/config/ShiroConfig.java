package com.example.dockerdemo.config;

import com.example.dockerdemo.realme.ShiroUserRealm;
import lombok.Data;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager() {
//        Map<String, List<String>> permissions = shiroProperties.getPermissions();
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        ShiroUserRealm userRealm = new ShiroUserRealm();
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setHashAlgorithmName("md5");
        userRealm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        return shiroFilter;
    }


}