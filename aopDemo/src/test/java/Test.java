import org.example.service.IUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //动态代理代理的是接口
        IUserService userService = context.getBean("userService", IUserService.class);
        userService.update();
    }
}
