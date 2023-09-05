import org.example.entity.BusiInfo;
import org.example.mapper.BusiInfoMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;

public class MyTest {
    @Test
    public void test() throws IOException {
//        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
//        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sessionFactory.openSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        mapper.selectUsers().forEach(System.out::println);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        BlogInfoMapper mapper = context.getBean(BlogInfoMapper.class);
//        List<BlogInfo> blogInfos = mapper.selectAll();
//        for (BlogInfo blogInfo : blogInfos) {
//            System.out.println(blogInfo);
//        }


        PlatformTransactionManager transactionManager = (PlatformTransactionManager) context.getBean(TransactionManager.class);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.execute(txStatus -> {
            BusiInfo busiInfo = new BusiInfo();
            busiInfo.setId(1077088257);
            busiInfo.setContent("将有");
            BusiInfo busiInfo2 = new BusiInfo();
            busiInfo2.setId(1400041473);
            BusiInfoMapper mapper = context.getBean(BusiInfoMapper.class);
            mapper.update(busiInfo);
            mapper.delete(busiInfo2);
            return null;
        });

    }
}
