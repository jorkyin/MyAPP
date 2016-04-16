package com.jorkyin.myapp.utils;

import java.text.DecimalFormat;

/**
 * SizeConverter
 * <ul>
 * <strong>Size Converter</strong>
 * <li>{@link #autoConverter(double)} 自动转化单位</li>
 * <li>{@link #byteToKB(double)} Byte转为KB</li>
 * <li>{@link #byteToMB(double)} Byte转为MB</li>
 * </ul>
 * @author Created by YinJian on 2016/4/16.
 */
public class  SizeConverter {

    /**
     * autoConverter
     *
     * @param byteSize
     * @return string
     */
    public static  String autoConverter(double byteSize) {
        DecimalFormat df = new DecimalFormat("0.00");

        if (byteToMB(byteSize) > 0.99) {
            return df.format(byteToMB(byteSize)) + "MB ";
        }
        if (byteToKB(byteSize) > 0.99) {
            return df.format(byteToKB(byteSize)) + "KB ";
        }
        return "0KB";
    }
    /**
     * autoConverter
     * 1024
     * @param byteSize
     * @return string
     */
    public static  double byteToKB(double byteSize) {
        return byteSize/1024;
    }
    /**
     * autoConverter
     *
     * @param byteSize
     * @return string
     */
    public static  double byteToMB(double byteSize) {
        return byteSize/(1024*1024);
    }
}
