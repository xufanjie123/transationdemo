package com.fanjiexu.entity;

import com.fanjiexu.entity.base.BaseEntity;
import org.springframework.stereotype.Repository;


public class Orders extends BaseEntity {

    private Long userTid;

    private Double amount;

    public Long getUserTid() {
        return userTid;
    }

    public void setUserTid(Long userTid) {
        this.userTid = userTid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}