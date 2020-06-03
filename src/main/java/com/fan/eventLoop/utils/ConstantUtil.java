package com.fan.eventLoop.utils;

public class ConstantUtil {
	
    public static int toPositive(int number) {
        return number & 0x7fffffff;
    }

}
