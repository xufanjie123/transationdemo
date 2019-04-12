package com.fanjiexu;

import com.fanjiexu.business.OrderBusiness;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransationdemoApplicationTests {

    @Autowired
    private OrderBusiness orderBusiness;

    @Test
    public void addUser() throws Exception {
//        orderBusiness.addUser();
        orderBusiness.addOrder("user1");
    }



    @Test
    public void contextLoads() {
    }

}

