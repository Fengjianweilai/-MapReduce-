package com.bigData.zjx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Date;


public class GenerateData {

    /**
     * 生成指定范围里的随机数
     */
    public static long randomNumber(long begin, long end){
            long rtn = begin + (long)(Math.random()*(end-begin));
          return rtn;
     }

    /**
     * 生成随机UUID
     */
    public static String randomUUID(){
        return UUID.randomUUID().toString().replace("_","").toUpperCase();
    }

    /**
     * 生成随机Data
     */
    public static Date randomData(String beginData,String endData){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = format.parse(beginData);
            Date end = format.parse(endData);
            if (start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }

    public static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

}







