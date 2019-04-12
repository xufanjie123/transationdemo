package com.fanjiexu.service;

import com.fanjiexu.entity.base.BaseEntity;
import com.fanjiexu.model.base.UILoginUser;
import com.fanjiexu.model.enums.ActiveStatus;
import com.fanjiexu.model.enums.GuidType;
import com.fanjiexu.util.SpringUtil;
import com.fanjiexu.util.TidService;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class InsertAndUpdateService {

    public void insertEntity(UILoginUser loginUser, Class mapperClass, BaseEntity entity) throws Exception {
        Object mapper = SpringUtil.getBean(mapperClass);
        if (entity.getTid() == null || entity.getTid() == 0) {
            entity.setTid(TidService.getTid());
        }
        entity.setCreateId(loginUser.getUserId());
        entity.setStatus(ActiveStatus.ENABLE);
        entity.setUpdateDate(new Date());
        entity.setLastGuid(TidService.GetGuid(GuidType.LOCK_ID));
        Class entityClass = entity.getClass();
        Method inertSelectiveMethod = mapperClass.getDeclaredMethod("insertSelective", new Class<?>[]{entityClass});
        inertSelectiveMethod.invoke(mapper, entity);
    }

    public void updateEntity(UILoginUser loginUser, Class mapperClass, BaseEntity entity) throws Exception {
        Object mapper = SpringUtil.getBean(mapperClass);
        entity.setUpdateDate(new Date());
        entity.setLastGuid(TidService.GetGuid(GuidType.LOCK_ID));
        entity.setUpdateId(loginUser.getUserId());
        Class entityClass = entity.getClass();
        Method inertSelectiveMethod = mapperClass.getDeclaredMethod("updateByPrimaryKeySelective", new Class<?>[]{entityClass});
        inertSelectiveMethod.invoke(mapper, entity);
    }

}
