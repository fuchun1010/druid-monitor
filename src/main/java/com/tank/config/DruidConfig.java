package com.tank.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import java.util.Optional;

/**
 * @author tank198435163.com
 */
@Configuration
public class DruidConfig {

  @Bean
  public ServletRegistrationBean druidStatViewServlet() {

    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

    //添加初始化参数：initParams
    //白名单：
    servletRegistrationBean.addInitParameter("allow", "");

    //登录查看信息的账号密码.
    String username = Optional.ofNullable(environment.getProperty("spring.datasource.monitor.login.username")).orElse("admin");
    String password = Optional.ofNullable(environment.getProperty("spring.datasource.monitor.login.password")).orElse("123456");
    servletRegistrationBean.addInitParameter("loginUsername", username);
    servletRegistrationBean.addInitParameter("loginPassword", password);
    //是否能够重置数据.
    servletRegistrationBean.addInitParameter("resetEnable", "false");
    return servletRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean druidStatFilter() {

    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

    //添加过滤规则.
    filterRegistrationBean.addUrlPatterns("/*");

    //添加不需要忽略的格式信息.
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
    return filterRegistrationBean;
  }

  @Bean
  public DruidStatInterceptor druidStatInterceptor() {
    DruidStatInterceptor dsInterceptor = new DruidStatInterceptor();
    return dsInterceptor;
  }

  @Bean
  @Scope("prototype")
  public JdkRegexpMethodPointcut druidStatPointcut() {
    JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
    pointcut.setPatterns("com.tank.service.*");
    return pointcut;
  }

  @Bean
  public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
    DefaultPointcutAdvisor defaultPointAdvisor = new DefaultPointcutAdvisor();
    defaultPointAdvisor.setPointcut(druidStatPointcut);
    defaultPointAdvisor.setAdvice(druidStatInterceptor);
    return defaultPointAdvisor;
  }


  @Autowired
  private Environment environment;
}
