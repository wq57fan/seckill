package org.seckill.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: wq57fan
 * @Date: 2018/8/5 14:08
 * @Description: Spring和JUnit整合
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        Assert.assertEquals(4, list.size());
    }
    /*
     *   SELECT * FROM seckill ORDER BY create_time DESC limit ?,?
     *   Total: 4
     * */

    @Test
    public void getById() throws Exception {
        Seckill seckill = seckillService.getById(1001);
        Assert.assertEquals(199, seckill.getNumber());
    }
    /*
     *   Preparing: SELECT * FROM seckill WHERE seckill_id=?
     *   Total: 1
     * */

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer = seckillService.exportSeckillUrl(1000);
        System.out.println("exposer.getMd5 = " + exposer.getMd5());
        System.out.println("exposer = " + exposer);
    }
    /*
    *   cf51fb5d20244f703b46be30a7b4d511
        Exposer{
            exposed=true,
            md5='cf51fb5d20244f703b46be30a7b4d511',
            seckillId=1000,
            now=0,
            start=0,
            end=0}
    * */

    @Test
    public void executeSeckill() throws Exception {
        //第二次执行会有：org.seckill.exception.RepeatKillException: seckill repeated
        //所以要用try-catch包住
        long id = 1000;
        long userPhone = 15207114730L;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(id);
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, userPhone, exposer.getMd5());
            System.out.println(seckillExecution);
        } catch (RepeatKillException e) {
            System.out.println(e.getMessage());
        } catch (SeckillCloseException e) {
            System.out.println(e.getMessage());
        }
    }
    /*
     *   SeckillExecution{
     *       seckillId=1001,
     *       state=1,
     *       stateInfo='秒杀成功',
     *       successKilled=SuccessKilled{
     *           seckillId=1001,
     *           userPhone=15207114730,
     *           state=0,
     *           createTime=null}}
     * */
}