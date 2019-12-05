package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @Author: wq57fan
 * @Date: 2018/8/4 23:41
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {

        long seckillId = 1000;
        long userPhone = 13476191877L;
        int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
        System.out.println("insertCount=" + insertCount);
    }
    /*
     * INSERT ignore INTO success_killed(seckill_id,user_phone,state) VALUES (?,?,0)
     * 第一次运行：insertCount=1
     * 第二次运行：insertCount=0
     * */

    @Test
    public void queryByIdWithSeckill() throws Exception {

        long seckillId = 1000L;
        long userPhone = 13476191877L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
    /*
        SELECT 
        	sk.seckill_id, sk.user_phone, sk.state, 
        	s.seckill_id "seckill.seckill_id", 
        	s.name "seckill.name", 
        	s.number "seckill", 
        	s.start_time "seckill.start_time", 
        	s.end_time "seckill.end_time", 
        	s.create_time "seckill.create_time" 
        FROM 
        	success_killed sk INNER JOIN seckill s 
        ON 
        	sk.seckill_id=s.seckill_id 
        WHERE 
        	sk.seckill_id=? AND sk.user_phone=?
    *   Total: 1
    *   SuccessKilled{
	    	seckillId=1000, 
	    	userPhone=13476191877, 
	    	state=0, 
	    	createTime=null}
        Seckill{
	        seckillId=1000, 
	        name='1000元秒杀iphone6', 
	        number=0, 
	        startTime=Tue May 22 08:00:00 CST 2018, 
	        endTime=Thu May 23 08:00:00 CST 2019, 
	        createTime=Sat Aug 04 22:52:12 CST 2018}
    * */
}