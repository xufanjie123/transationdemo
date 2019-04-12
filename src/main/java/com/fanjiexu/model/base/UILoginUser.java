package com.fanjiexu.model.base;

import lombok.Data;

@Data
public class UILoginUser {

    private long userId;

    private long companyId;

    private String name;

    private String loginName;

    private String workNum;

    private String mobile;

    private String email;

    private String[] permissions;

    private String sign;
}
