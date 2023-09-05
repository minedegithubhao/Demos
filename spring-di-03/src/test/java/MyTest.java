import org.example.service.AccountService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cxdpc
 * @date 2023-09-03 22:13
 */
public class MyTest {

//    @Autowired
//    private ApplicationContext applicationContext;

    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-02.xml");
        AccountService accountService = applicationContext.getBean("accountServiceImpl", AccountService.class);
        System.out.println("before money transfer");
        System.out.println("Account 1 balance :" + accountService.getAccount(1).getBalance());
        System.out.println("Account 2 balance :" + accountService.getAccount(2).getBalance());
        accountService.transferMoney(1, 2, 5.0);
        System.out.println("after money transfer");
        System.out.println("Account 1 balance :" + accountService.getAccount(1).getBalance());
        System.out.println("Account 2 balance :" + accountService.getAccount(2).getBalance());

//        AccountService accountServiceImpl = applicationContext.getBean("accountServiceImpl", AccountService.class);
//        System.out.println(accountServiceImpl.getAccount(1));
    }
}
