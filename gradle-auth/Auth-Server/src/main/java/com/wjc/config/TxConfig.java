package com.wjc.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;
/**
 * 声明式事务配置
 * Created by wangjc
 */
@Configuration
public class TxConfig {
  @Autowired
  private DataSourceTransactionManager transactionManager;

  @Bean(name = "txAdvice")
  public TransactionInterceptor getAdvisor() throws Exception {
    Properties properties = new Properties();
    properties.setProperty("get*", "PROPAGATION_REQUIRED,-Exception,readOnly");
    properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
    properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
    TransactionInterceptor transactionInterceptor = new TransactionInterceptor(transactionManager,properties);
    return transactionInterceptor;
  }
  @Bean
  public BeanNameAutoProxyCreator txProxy() {
    BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
    creator.setInterceptorNames("txAdvice");
    creator.setBeanNames("*Service", "*ServiceImpl");
    creator.setProxyTargetClass(true);
    return creator;
  }
}