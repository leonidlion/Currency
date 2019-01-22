package com.currency.dev.leo.utils;

public class StringUtils {
    public static String getCurrencyNameWithSlash(String name){
        if(name.length() == 6)
            return name.substring(0, 3) + "/" + name.substring(3, name.length());
        else return name;
    }
}
