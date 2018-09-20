package com.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateControlUtil {

    /**
     * 查询时间处理
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public Map getDateMap(String startTime, String endTime) throws ParseException {
        Map dateMap = new HashMap();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        //只选择了一个时间，则筛选为单日的数据
        if ((StringUtils.isNotEmpty(startTime) && StringUtils.isEmpty(endTime)) || (StringUtils.isEmpty(startTime) && StringUtils.isNotEmpty(endTime))) {
            if (StringUtils.isNotEmpty(startTime)) {
                cal.setTime(format.parse(startTime));
            }
            if (StringUtils.isNotEmpty(endTime)) {
                cal.setTime(format.parse(endTime));
            }
        }

        if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
            //第一个时间=第二个时间，则筛选为单日的数据
            if (startTime.equals(endTime)) {
                cal.setTime(format.parse(startTime));
            } else {
                cal.setTime(format.parse(endTime));
                cal.add(Calendar.DAY_OF_MONTH, 1);
                //结束查询时间
                String backEndTime = format.format(cal.getTime());
                dateMap.put("startTime", startTime);
                dateMap.put("endTime", backEndTime);
                return dateMap;
            }
        }
        //开始查询时间
        String backStartTime = format.format(cal.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        //结束查询时间
        String backEndTime = format.format(cal.getTime());
        dateMap.put("startTime", backStartTime);
        dateMap.put("endTime", backEndTime);
        return dateMap;
    }
}
