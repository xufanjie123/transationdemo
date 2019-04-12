package com.fanjiexu.mapper.user;

import com.fanjiexu.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long tid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long tid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectByUserName(String username);

    int updateAmount(double amount);
}