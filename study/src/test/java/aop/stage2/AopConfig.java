package aop.stage2;

import aop.stage1.TransactionAdvice;
import aop.stage1.TransactionAdvisor;
import aop.stage1.TransactionPointcut;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class AopConfig {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public TransactionAdvice transactionAdvice() {
        return new TransactionAdvice(platformTransactionManager);
    }

    @Bean
    public TransactionPointcut transactionPointcut() {
        return new TransactionPointcut();
    }

    @Bean
    public TransactionAdvisor transactionAdvisor() {
        return new TransactionAdvisor(transactionPointcut(), transactionAdvice());
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }
}
