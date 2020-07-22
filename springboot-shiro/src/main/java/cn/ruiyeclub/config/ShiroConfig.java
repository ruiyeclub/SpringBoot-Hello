package cn.ruiyeclub.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 拦截功能以及授权功能都在这里配置
 * Shiro可控制的Web请求必须经过Shiro主过滤器的拦截
 * @author Ray。
 * @create 2020-03-11 14:28
 */
@Configuration
public class ShiroConfig {
    //1.创建realm对象 需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //2.DefaultWebSecurityManager权限管理
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //3.ShiroFilterFactoryBean过滤器配置
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /**
         * anon：无需认证就可以访问
         * authc：必须认证了才能访问
         * user：必须拥有 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权
        filterMap.put("/user/add","perms[user:add]"); //进入需要授权（授权规则用户后面接：add）才可以进入add
        filterMap.put("/user/update","perms[user:update]");

        //拦截功能 *拦截功能写在上面会错*
//        filterMap.put("/user/add","anon");
//        filterMap.put("/user/update","authc");
        filterMap.put("/user/*","authc"); //表示访问user接口的资源都要认证
        bean.setFilterChainDefinitionMap(filterMap);

        //******处理权限不够或者需要授权的业务********
        //如果没有权限authc 设置登录的请求
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    /**
     * 配置ShiroDialect，用于Shiro和thymeleaf标签配合使用
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}