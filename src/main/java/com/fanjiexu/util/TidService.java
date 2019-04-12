package com.fanjiexu.util;

import com.fanjiexu.config.AppConfig;
import com.fanjiexu.model.enums.GuidType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static com.fanjiexu.config.AppConfig.*;

/**
 * Tid服务
 *
 * @author cy
 **/
@Component
public class TidService {



    {
        guidDic.put(GuidType.TABLE_ID, TID_INIT);
        guidDic.put(GuidType.FILE_NAME_ID, TID_INIT);
        guidDic.put(GuidType.ERROR, TID_INIT);
        guidDic.put(GuidType.LOCK_ID, TID_INIT);
    }

    static Random random = new Random();

    private static HashMap<Integer, Long> guidDic = new HashMap<Integer, Long>();

    public static long getTid() {
        return GetGuid(GuidType.TABLE_ID);
    }

    public static long GetGuid(Integer guidTyp) {
        synchronized (guidDic) {
            guidDic.put(guidTyp, guidDic.get(guidTyp) + 1);
            if (guidDic.get(guidTyp) > TID_MAX) {
                guidDic.put(guidTyp, TID_INIT);
                try {
                    Thread.sleep(1001);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            AppConfig appConfig = SpringUtil.getBean(AppConfig.class);
            return (System.currentTimeMillis() - TID_SUB_VALUE) * 10000L + guidDic.get(guidTyp) * 100L + appConfig.getServerId() + EIGHT_HOUR_TOTAL_MILLISECONDS;
        }
    }

    public static Date GetDateTimeFromTid(long tid) {
        long totalMilliSeconds = tid / 10000;
        return new Date(TID_MIN_DATE.getTime() + totalMilliSeconds);
    }

    public static long GetIdByDatetime(Date dt) {
        return (dt.getTime() - TID_MIN_DATE.getTime()) * 10000;
    }

}
