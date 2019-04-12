package com.fanjiexu.entity;

import com.fanjiexu.entity.base.BaseEntity;
import org.springframework.stereotype.Repository;


public class User extends BaseEntity {

    private String userName;

    private Double money;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}