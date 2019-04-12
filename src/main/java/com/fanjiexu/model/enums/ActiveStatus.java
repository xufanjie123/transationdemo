package com.fanjiexu.model.enums;


import com.sun.org.glassfish.gmbal.Description;

public class ActiveStatus {
    @Description(value = "启用")
    public static int ENABLE = 1;
    @Description(value = "禁用")
    public static int DISABLE = 2;
    @Description(value = "删除")
    public static int DELETED = 3;

}


