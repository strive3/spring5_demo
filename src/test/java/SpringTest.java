import com.spring.study.bean.MyFactoryBean;
import com.spring.study.bean.Temp;
import com.spring.study.bean.TestArthas;
import com.spring.study.bean.User;
import com.spring.study.config.SpringConfig;
import com.spring.study.jdbc.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
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

    @Test
    public void testBeanDefiniton(){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        User user = context.getBean("useraaaa", User.class);
        //
        System.out.println(user.toString());
    }


    @Test
    public void testSpringBean(){
        //创建一个简单的注册器        map  id/name->BeanDefinition
        BeanDefinitionRegistry registry = new DefaultListableBeanFactory();

        //创建bean定义读取器
        BeanDefinitionReader beanDefinitionRead = new XmlBeanDefinitionReader(registry);

        //加载配置文件
        beanDefinitionRead.loadBeanDefinitions("bean.xml");
        System.out.println(registry.getBeanDefinitionCount());

        //----》自定义bean
        BeanDefinition my = new RootBeanDefinition(User.class);
        registry.registerBeanDefinition("user1", my);
        BeanDefinition beanDefinition1 = registry.getBeanDefinition("user1");


        //从注册器中获取bean的定义
        BeanDefinition beanDefinition = registry.getBeanDefinition("user");
        System.out.println(beanDefinition.toString() + "\n" + beanDefinition1);
    }

    @Test
    public void TestArthas() throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        TestArthas arthas = context.getBean("testArthas", TestArthas.class);

        arthas.a();
    }


    @Test
    public void testFactoryBean() throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        MyFactoryBean myFactortBean = context.getBean("&myFactoryBean", MyFactoryBean.class);
        Temp temp = context.getBean("myFactoryBean", Temp.class);
        User user = context.getBean("user", User.class);

        System.out.println(myFactortBean.getObject().toString());
        System.out.println(temp.toString());
        System.out.println(user.toString());
    }
}
