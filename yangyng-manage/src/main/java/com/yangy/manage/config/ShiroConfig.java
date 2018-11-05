package com.yangy.manage.config;

import com.yangy.manage.bean.ShiroRealm;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * <p>
 * shiro权限管理配置文件
 * </P>
 *
 * @author yang yang
 * @create 2018/11/5
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/**", "anon");

        filterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        //
        filterFactoryBean.setLoginUrl("/unauth");
        return filterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
//        securityManager.setSessionManager(null);
//        securityManager.setCacheManager(null);
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authAttrSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    @Bean
    public AllowAllCredentialsMatcher matcher() {
        AllowAllCredentialsMatcher matcher = new AllowAllCredentialsMatcher();
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(1);
        return matcher;
    }

    @Bean
    public ShiroRealm getRealm() {
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(matcher());
        return realm;
    }


}