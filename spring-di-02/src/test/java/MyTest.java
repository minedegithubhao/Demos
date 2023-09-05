import org.example.config.Ch2BeanConfiguration;
import org.example.service.AccountService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author cxdpc
 * @date 2023-09-03 22:03
 */
public class MyTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ch2BeanConfiguration.class);
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        System.out.println("before money transfer");
        System.out.println("Account 1 balance :" + accountService.getAccount(1).getBalance());
        System.out.println("Account 2 balance :" + accountService.getAccount(2).getBalance());
        accountService.transferMoney(1, 2, 5.0);
        System.out.println("after money transfer");
        System.out.println("Account 1 balance :" + accountService.getAccount(1).getBalance());
        System.out.println("Account 2 balance :" + accountService.getAccount(2).getBalance());
    }
}
