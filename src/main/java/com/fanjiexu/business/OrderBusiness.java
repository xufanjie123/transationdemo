package com.fanjiexu.business;

import com.fanjiexu.config.AppConfig;
import com.fanjiexu.entity.Orders;
import com.fanjiexu.entity.User;
import com.fanjiexu.mapper.order.OrdersMapper;
import com.fanjiexu.mapper.user.UserMapper;
import com.fanjiexu.service.InsertAndUpdateService;
import com.fanjiexu.util.TidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderBusiness {

    @Autowired
    private InsertAndUpdateService insertAndUpdateService;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private OrdersMapper ordersMapper;

    public void addUser() throws Exception {
        User user = new User();
        user.setTid(TidService.getTid());
        user.setUserName("user1");
        user.setMoney(10000000.0);
//        userMapper.insertSelective(user);
        insertAndUpdateService.insertEntity(AppConfig.SystemAdmin, UserMapper.class, user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addOrder(String name) throws Exception {
        List<User> users = userMapper.selectByUserName(name);
        User user = users.get(0);
        Orders orders = new Orders();
        orders.setUserTid(user.getTid());
        orders.setAmount(1000.0);
        userMapper.updateAmount(orders.getAmount());
        insertAndUpdateService.insertEntity(AppConfig.SystemAdmin, OrdersMapper.class, orders);
    }

}
