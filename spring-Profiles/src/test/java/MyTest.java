import org.example.mapper.UserDao;
import org.example.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author cxdpc
 * @date 2023-09-05 08:06
 */
public class MyTest {

    //-Dspring.profiles.active=dev多环境配置的一种
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.getNameById("1"));
    }
}
