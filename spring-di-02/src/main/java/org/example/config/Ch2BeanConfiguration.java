package org.example.config;

import org.example.dao.AccountDao;
import org.example.dao.impl.AccountDaoInMemoryImpl;
import org.example.service.AccountService;
import org.example.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cxdpc
 * @date 2023-09-03 21:59
 */
@Configuration
public class Ch2BeanConfiguration {
    @Bean
    public AccountDao accountDao(){
        return new AccountDaoInMemoryImpl();
    }
    @Bean
    public AccountService accountService(){
        AccountServiceImpl bean = new AccountServiceImpl();
        bean.setAccountDao(accountDao());
        return bean;
    }
}
