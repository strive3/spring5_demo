package jedis;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author duxiaopeng
 * @Date 2021/3/2 12:48 下午
 * @Description
 */
public class TestJedis {

    private static Jedis jedis = new Jedis("152.136.142.49", 6379);
    {
        jedis.auth("root");//这里填写密码
    }

    @Test
    public void testPing(){
        System.out.println(jedis.ping());
        jedis.close();
    }


    @Test
    public void testKey(){
        System.out.println("清空数据\t" + jedis.flushDB());
        System.out.println("判断某个建是否存在\t" + jedis.exists("username"));
        System.out.println("新增<'username','strive'>的键值对\t" + jedis.set("username", "strive"));
        System.out.println("新增<'auto','root'>的键值对\t" + jedis.set("auto", "root"));
        System.out.println("系统中所有的键\t" + jedis.keys("*"));//返回的是set集合
        System.out.println("删除键\t" + jedis.del("username"));
        System.out.println("查看键所存储的值类型\t" + jedis.type("auto"));
        System.out.println("随机返回key空间的一个\t" + jedis.randomKey());
        System.out.println("重命名key\t" + jedis.rename("auto","auu"));
        System.out.println("取出改后的name\t" + jedis.get("auu"));
        System.out.println("按索引查询\t" + jedis.select(0));
        System.out.println("返回当前数据库中的key的数目\t" + jedis.dbSize());
        System.out.println("删除当前数据库中的所有key\t" + jedis.flushDB());
        System.out.println("删除所有数据库中的所有key\t" + jedis.flushAll());

        jedis.close();
    }

    /**
     * @Author du-xp
     * @Date 2021/3/2
     * @return: void
     * @Description TODO 事务
     */
    @Test
    public void testTX(){
        //1、开启事务
        Transaction multi = jedis.multi();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("username", "strive");
        String string = jsonObject.toJSONString();
        //jedis.watch("hello");
        try{
            multi.set("user1", string);
            multi.set("user2", string);

            int i = 1/0;
            //2、执行事务
            multi.exec();
        } catch (Exception e){
            e.printStackTrace();
            //放弃事务
            multi.discard();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }

    }

    @Test
    public void testGc(){
        HashMap<String, String> map = new HashMap<>();
        while (true){
            map.put("1", "1");
        }
    }


    @Test
    public void testWater() {
        System.out.println("请输入五个数");

        int[] l = new int[5];

        for (int i = 0; i < 5; i++){

            l[i] = new Scanner(System.in).nextInt();
        }

        System.out.println(l.toString());
    }

    public static void main(String[] args) {
        System.out.println("请输入五个数");

//        int[] l = new int[5];
//
//        for (int i = 0; i < 5; i++){
//
//            l[i] = new Scanner(System.in).nextInt();
//        }
        String as = "aa";
        byte[] bytes = as.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            int j = bytes[i];
            System.out.println(j);
        }
//        System.out.println(l.toString());
    }
}
