import com.spring.study.bean.User;
import com.spring.study.config.SpringConfig;
import com.spring.study.jdbc.dao.AccountDao;
import com.spring.study.jdbc.dao.impl.AccountDaoImpl;
import com.spring.study.jdbc.entity.Account;
import com.spring.study.jdbc.proxy.JDKProxy;
import com.spring.study.jdbc.service.AccountService;
import com.spring.study.jdbc.service.AccountService_2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @Author duxiaopeng
 * @Date 2020/12/13 12:10 下午
 * @Description TODO
 */
@SpringJUnitConfig(classes = SpringConfig.class)
public class SpringJunit5 {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountService_2 accountService_2;
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private User user;


    @Test
    void name() {
        accountService.reduceAndAddMoney();
    }

    @Test
    void name3() {
        accountService_2.reduceAndAddMoney();
    }

    @Test
    void name1() {
        accountDao.addMoney();
    }

    @Test
    void name2() {
        System.out.println(user.toString());
    }

    @Test
    void testProxy(){
        AccountDao accountDaoImpl = (AccountDao) JDKProxy.getProxy(new AccountDaoImpl());
        Account lucy = accountDaoImpl.getAccount("lucy");
        System.out.println(lucy);
    }

}
