import com.spring.study.bean.MyFactoryBean;
import com.spring.study.bean.Temp;
import com.spring.study.bean.TestArthas;
import com.spring.study.bean.User;
import com.spring.study.config.SpringConfig;
import com.spring.study.jdbc.aop.MyAspect;
import com.spring.study.jdbc.dao.AccountDao;
import com.spring.study.jdbc.dao.impl.AccountDaoImpl;
import com.spring.study.jdbc.entity.Account;
import com.spring.study.jdbc.proxy.JDKProxy;
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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        service.getAccount("lucy");

//        service.reduceAndAddMoney();
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

    @Test
    public void test1(){
        int[] arr = new int[10];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        arr[4] = 1;
        arr[5] = 2;
        arr[6] = 2;
        arr[7] = 1;
        arr[8] = 1;
        arr[9] = 1;

        int length = arr.length;
        int count = 0;
        int temp = arr[0];
        for (int i = 0; i < length; i++) {
            if (arr[i] == temp){
                count++;
                if (count >= length/2 - 1)
                    break;
            } else {
                count = 0;
                temp = arr[i];
            }
            if (i == length - 1){
                System.out.println("没有连续出现的数字");
            }
        }
        System.out.println(temp);
    }

    @Test
    public void test2(){
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("2", "2");//值和value 都不可以为空
        hashtable.put("1", "1");
        Set<Map.Entry<String, String>> entries = hashtable.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test
    public void test3(){
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(3);
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(3);
    }

    @Test
    void testProxy(){
        AccountDao accountDaoImpl = (AccountDao) JDKProxy.getProxy(new AccountDaoImpl());
        int a = accountDaoImpl.get1();
        System.out.println(a);
    }

    @Test
    void testInteger(){
        Integer a = 127;
        Integer b = 127;

        Integer c = new Integer(127);
        System.out.println(a == b);
        System.out.println(a == c);
    }

    @Test
    void testJava8(){
        List<Integer> integers = new ArrayList<>();
        integers.add(5);
        integers.add(2);
        integers.add(4);
        integers.add(6);
        integers.forEach(a -> System.out.println(a));
        integers = integers.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());
        integers.forEach(System.out::println);
    }

    @Test
    void testIntern(){
        String s1 = new StringBuilder().append("计算机").append("原理").toString();
        System.out.println(s1.intern() == s1);
        String s2 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s2.intern() == s2);
    }

    @Test
    void testList(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        String bbb = new String("BBB");
        strings.add(new String("BBB"));

        System.out.println(strings.contains(bbb));
    }

}
