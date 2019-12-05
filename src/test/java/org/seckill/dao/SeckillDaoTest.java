package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: wq57fan
 * @Date: 2018/8/4 23:26
 * @Description: 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long seckillId = 1000;
        Seckill seckill = seckillDao.queryById(seckillId);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }
    /*
        SELECT * FROM seckill WHERE seckill_id=?
    *   1000元秒杀iphone6
        Seckill{
            seckillId=1000, 
            name='1000元秒杀iphone6', 
            number=99, 
            startTime=Tue May 22 08:00:00 CST 2018, 
            endTime=Thu May 23 08:00:00 CST 2019, 
            createTime=Sat Aug 04 22:52:12 CST 2018}
    * */

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }
    /*
        SELECT * FROM seckill ORDER BY create_time DESC limit ?,?
    *   Seckill{seckillId=1000, name='1000元秒杀iphone6', number=99, startTime=Tue May 22 08:00:00 CST 2018, endTime=Thu May 23 08:00:00 CST 2019, createTime=Sat Aug 04 22:52:12 CST 2018}
        Seckill{seckillId=1001, name='500元秒杀iPad2', number=200, startTime=Sun May 22 08:00:00 CST 2016, endTime=Thu May 23 08:00:00 CST 2019, createTime=Sat Aug 04 22:52:12 CST 2018}
        Seckill{seckillId=1002, name='300元秒杀小米4', number=300, startTime=Sun May 22 08:00:00 CST 2016, endTime=Thu May 23 08:00:00 CST 2019, createTime=Sat Aug 04 22:52:12 CST 2018}
        Seckill{seckillId=1003, name='200元秒杀红米note', number=400, startTime=Sun May 22 08:00:00 CST 2016, endTime=Mon May 23 08:00:00 CST 2016, createTime=Sat Aug 04 22:52:12 CST 2018}
    * */

    @Test
    public void reduceNumber() throws Exception {
        long seckillId = 1000;
        Date date = new Date();
        int updateCount = seckillDao.reduceNumber(seckillId, date);
        System.out.println(updateCount);
    }
    /*
     *   UPDATE seckill SET number = number-1 WHERE seckill_id=? AND start_time <= ? AND end_time >= ? AND number > 0;
     *   1
     * */

}