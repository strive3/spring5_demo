import com.spring.study.bean.User;
import com.spring.study.config.SpringConfig;
import com.spring.study.jdbc.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
    public void test(){
        System.out.println("hello world");
    }

    @Test
    public void testSpringXml(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        User user = context.getBean("user", User.class);

        System.out.println(user.toString());
    }

    @Test
    public void testSpringClass(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        User user = context.getBean("user", User.class);
        //
        System.out.println(user.toString());
    }



    @Test
    public void testSpringJdbc(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        AccountService service = context.getBean("accountService", AccountService.class);
        //
        System.out.println(service.getAccount("lucy"));

        service.reduceAndAddMoney();
    }

    @Test
    public void testSpringstring(){
//        String a = "select * from t order by c";
        String a = "select * from t";
        int i = a.indexOf("order");
        String b = a.substring(0,i);
        System.out.println(b);
    }
}
