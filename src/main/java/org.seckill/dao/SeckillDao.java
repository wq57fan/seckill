package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * @Author: wq57fan
 * @Date: 2018/8/4 15:02
 * @Description:
 */
public interface SeckillDao {

    /*减库存。返回更新的行数。*/
    int reduceNumber(@Param("seckillId")long seckillId, @Param("killTime") Date killTime);

    /*根据id查询秒杀对象*/
    Seckill queryById(long seckillId);

    /*根据偏移量查询秒杀商品列表*/
    /*
     * Parameter 'offset' not found. Available parameters are [arg1, arg0, param1, param2]
     * 当定义多个参数的时候，需要使用@Param注解进行
     * */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
