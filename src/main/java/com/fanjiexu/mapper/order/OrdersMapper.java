package com.fanjiexu.mapper.order;

import com.fanjiexu.entity.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper {
    int deleteByPrimaryKey(Long tid);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Long tid);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}