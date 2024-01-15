package com.xingyun.easypaycommon.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class TimeUtil {

    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static LocalDateTime getTodayStartTime(){
        OffsetDateTime offsetDateTime = LocalDateTime.now().atOffset(ZoneOffset.UTC);
        LocalDate localDateUTC8 = offsetDateTime.withOffsetSameInstant(ZoneOffset.ofHours(8)).toLocalDate();
        return getDayStartTime(localDateUTC8.toString());
    }

    /**
     * 获取某日开始时间
     * YYYY-MM-dd 2012-12-12
     * @param day
     * @return
     */
    public static LocalDateTime getDayStartTime(String day){
        Instant startTime = LocalDateTime.parse(day + " 00:00:00", timeFormatter).toInstant(ZoneOffset.ofHours(8));
        return LocalDateTime.ofInstant(startTime,ZoneOffset.UTC);
    }

    public static LocalDateTime getTodayEndTime(){
        OffsetDateTime offsetDateTime = LocalDateTime.now().atOffset(ZoneOffset.UTC);
        LocalDate localDateUTC8 = offsetDateTime.withOffsetSameInstant(ZoneOffset.ofHours(8)).toLocalDate();
        return getDayEndTime(localDateUTC8.toString());
    }

    /**
     * 获取某日结束时间
     * YYYY-MM-dd 2012-12-12
     * @param day
     * @return
     */
    public static LocalDateTime getDayEndTime(String day){
        Instant startTime = LocalDateTime.parse(day + " 23:59:59", timeFormatter).toInstant(ZoneOffset.ofHours(8));
        return LocalDateTime.ofInstant(startTime,ZoneOffset.UTC);
    }

    /**
     * 获取月初时间
     * YYYY-MM  2012-12
     * @param month
     * @return
     */
    public static LocalDateTime getMonthStartTime(String month){
        Instant startTime = LocalDateTime.parse(month+"-01" + " 00:00:00", timeFormatter).toInstant(ZoneOffset.ofHours(8));
        return LocalDateTime.ofInstant(startTime,ZoneOffset.UTC);
    }

    /**
     * 获取月末时间
     * YYYY-MM  2012-12
     * @param month
     * @return
     */
    public static LocalDateTime getMonthEndTime(String month){
        LocalDate date = LocalDate.parse(month+"-01", dayFormatter);
        LocalDate last = date.with(TemporalAdjusters.lastDayOfMonth());
        Instant endTime = LocalDateTime.parse(last + " 23:59:59", timeFormatter).toInstant(ZoneOffset.ofHours(8));
        return LocalDateTime.ofInstant(endTime,ZoneOffset.UTC);

    }


}
