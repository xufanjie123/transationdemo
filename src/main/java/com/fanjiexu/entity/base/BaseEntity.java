package com.fanjiexu.entity.base;


import java.util.Date;

public class BaseEntity {
    private Long tid;

    private Long createId;

    private Long proxyUserId;

    private Long lastGuid;

    private Integer clone;

    private Long updateId;

    private Date updateDate;

    private Integer status;

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getProxyUserId() {
        return proxyUserId;
    }

    public void setProxyUserId(Long proxyUserId) {
        this.proxyUserId = proxyUserId;
    }

    public Long getLastGuid() {
        return lastGuid;
    }

    public void setLastGuid(Long lastGuid) {
        this.lastGuid = lastGuid;
    }

    public Integer getClone() {
        return clone;
    }

    public void setClone(Integer clone) {
        this.clone = clone;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
