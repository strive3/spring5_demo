import com.spring.study.bean.User;
import com.spring.study.config.SpringConfig;
import com.spring.study.jdbc.dao.AccountDao;
import com.spring.study.jdbc.service.AccountService;
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
    private AccountDao accountDao;

    @Autowired
    private User user;


    @Test
    void name() {
        accountService.reduceAndAddMoney();
    }

    @Test
    void name1() {
        accountDao.addMoney();
    }

    @Test
    void name2() {
        System.out.println(user.toString());
    }



}
