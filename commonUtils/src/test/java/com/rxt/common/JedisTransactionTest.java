/**
 * FileName: JedisTransactionTest
 * Author:   Ren Xiaotian
 * Date:     2018/11/4 11:41
 */

package com.rxt.common;

import com.rxt.common.utils.DistributedLock.redis.JedisDLock;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Jedis Transaction测试
 */
public class JedisTransactionTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.117.134", 6379);
        jedis.auth("123456");

        Transaction transaction = jedis.multi();
//        transaction.set("name", "zhangsan");
//        List<Object> result = transaction.exec();
//        System.out.println(result);

        List<Object> result = null;
        Response<Long> response1 = transaction.setnx("name", "lisi");
        Response<Long> response2 = transaction.setnx("name", "wangwu");
//        result = transaction.exec();    //提交事务
        System.out.println(result);

        System.out.println(response1);
        System.out.println(response2);

        if ("1".equals(result.get(0)) && "1".equals(result.get(1))){    //如果事务的两步都执行成功，则返回
            System.out.println("事务成功");
        }else{
            transaction.discard();    //取消事务
            System.out.println("事务已取消");
        }

    }
}
