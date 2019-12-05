package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * @Author: wq57fan
 * @Date: 2018/8/4 15:06
 * @Description:
 */
public interface SuccessKilledDao {

    /*插入购买明细。要可过滤重复。返回插入的行数。*/
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /*根据id和手机号查询SuccessKilled并携带秒杀产品对象实体*/
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
