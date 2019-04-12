package com.fanjiexu.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {

    private String name;

    private Date birthDay;

    private int id;

}
