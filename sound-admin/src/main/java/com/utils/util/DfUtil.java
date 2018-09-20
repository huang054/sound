package com.utils.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by lichaojie on 2017/8/12.
 */
public class DfUtil {

    /**
     * double
     * 科学计数法转数字
     */
    public static DecimalFormat transform() {
        //设置小数点6位
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.setMaximumFractionDigits(6);
        return df;
    }
}
