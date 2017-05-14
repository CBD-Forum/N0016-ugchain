package com.ugc.gameserver.util;


import com.mysql.jdbc.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by yuanshichao on 2016/12/14.
 */
public class DateUtil {

    public static final String HC_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 将目标日期对象按指定的日期格式转换为日期字符串
     *
     * @param target
     *            目标对象
     * @param pattern
     *            日期格式
     * @return 日期字符串
     */
    public static String formatDate(Date target, String pattern) {
        String formattedDate = null;
        DateFormat dateFormat = getDateFormat(pattern);
        if (dateFormat != null) {
            formattedDate = dateFormat.format(target);
        } else {
            throw new IllegalArgumentException("not support pattern: " + pattern);
        }
        return formattedDate;
    }

    public static DateFormat getDateFormat(String pattern) {
        if (StringUtils.isNullOrEmpty(pattern)) {
            throw new IllegalArgumentException("arg 'pattern' can't be empty");
        }
        DateFormat dateFormat = null;
        try {
            dateFormat = new SimpleDateFormat(pattern);
        } catch (IllegalArgumentException e) {
            // do nothing
        }
        return dateFormat;
    }


}
