package com.yj.reservation.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间操作相关工具包包括： 1.时间格式化：从时间戳到"yyyy-MM-dd HH:mm:ss"
 */
public class DateUtil {
    /**
     * 默认日期格式化模型
     */
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    public static final String PATTERN_HH_MM_SS = "HH:mm:ss";
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String SMALL_HOURS_ZERO = " 00:00:00";
    public static final String DEFAUL_PATTERN = PATTERN_YYYY_MM_DD;

    public static final long ONE_MINUTE = 1000 * 60;
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    public static final long ONE_DAY = ONE_HOUR * 24;
    public static final long THIRTY_DAY = ONE_DAY * 30;

    public static final Map<String, DateTimeFormatter> dateTimeFormatterCacheMap = new HashMap();

    static {
        dateTimeFormatterCacheMap.put(PATTERN_YYYY_MM_DD_HH_MM_SS,
                DateTimeFormatter.ofPattern(PATTERN_YYYY_MM_DD_HH_MM_SS));
        dateTimeFormatterCacheMap.put(PATTERN_YYYY_MM_DD, DateTimeFormatter.ISO_DATE);
        dateTimeFormatterCacheMap.put(PATTERN_YYYYMMDD, DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * Date转换为LocalDateTime
     */

    private static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
//		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime;
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    private static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * LocalDate转换为Date
     *
     * @param localDate
     */
    private static Date localDate2Date(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        Date date = Date.from(zdt.toInstant());
        return date;
    }

    /**
     * 默认date转换为string的格式为yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static final String dateToStr(Date date) {
        return dateToStr(date, DEFAUL_PATTERN);
    }

    public static final String dateToStr(Date date, String pattern) {
        DateTimeFormatter formatter = createDateTimeFormatter(pattern);
        return dateToStr(date, formatter);
    }

    public static final String dateToStr(Date date, DateTimeFormatter formatter) {
        if (date == null) {
            return null;
        }
        LocalDateTime ldt = date2LocalDateTime(date);
        return ldt.format(formatter);
    }

    public static final String dateToStr(LocalDateTime ldt) {
        return dateToStr(ldt, DEFAUL_PATTERN);
    }

    public static final String dateToStr(LocalDateTime ldt, String pattern) {
        DateTimeFormatter formatter = createDateTimeFormatter(pattern);
        return ldt.format(formatter);
    }

    public static final String dateToStr(LocalDateTime ldt, DateTimeFormatter formatter) {
        return ldt.format(formatter);
    }

//	public static final Date longToDate(long timeStamp) {
//		return new Date(timeStamp);
//	}

    /**
     * 尝试着将某个字符串转换成日期
     *
     * @param dateStr
     * @return
     */
    public static final Date tryConvertStrToDate(String dateStr) {
        dateStr = dateStr.trim();
        if (dateStr.indexOf(":") < 0) {
            dateStr += " 00:00:00";
        }
        String[] patterns = new String[]{PATTERN_YYYY_MM_DD_HH_MM_SS};
        for (String pattern : patterns) {
            try {
                return strToDate(dateStr, pattern);
            } catch (Exception e) {
                logger.warn("尝试转换日期失败:" + dateStr + "," + pattern);
            }
        }
        logger.warn("not found match format pattern:" + dateStr);
        return null;
    }

    /**
     * 默认date转换为string的格式为yyyy-MM-dd
     *
     * @param dateStr
     * @return
     */
    public static final Date strToDate(String dateStr) {
        return localDate2Date(strToLocalDate(dateStr, PATTERN_YYYY_MM_DD));
    }

    public static final Date strToDate(String dateStr, String pattern) {
        return localDateTime2Date(strToLocalDateTime(dateStr, pattern));
    }

    public static final LocalDateTime strToLocalDateTime(String dateStr) {
        return strToLocalDateTime(dateStr, DEFAUL_PATTERN);
    }

    public static final LocalDateTime strToLocalDateTime(String dateStr, String pattern) {
        DateTimeFormatter formatter = createDateTimeFormatter(pattern);
        return strToLocalDateTime(dateStr, formatter);
    }

    public static final LocalDateTime strToLocalDateTime(String dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static final LocalDate strToLocalDate(String dateStr) {
        return strToLocalDate(dateStr, DEFAUL_PATTERN);
    }

    public static final LocalDate strToLocalDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = createDateTimeFormatter(pattern);
        return strToLocalDate(dateStr, formatter);
    }

    public static final LocalDate strToLocalDate(String dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    public static final DateTimeFormatter createDateTimeFormatter(String pattern) {
        DateTimeFormatter formatter = dateTimeFormatterCacheMap.get(pattern);
        if (formatter == null) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }
        return formatter;
    }

    public static Date dateToISODate(Date dateStr) {
        Date parse = null;
        try {
            // 解析字符串时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            parse = format.parse(format.format(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

}
