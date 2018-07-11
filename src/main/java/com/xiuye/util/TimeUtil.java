package com.xiuye.util;

public class TimeUtil {

	public static long STIME = -1;
	public static long ETIME = -1;

	public static long start(){

		return STIME = System.currentTimeMillis();
	}
	public static long end(){
		return ETIME = System.currentTimeMillis();
	}
	public static long spending(){
		return ETIME - STIME;
	}
	public static long outCost(){

		ETIME = System.currentTimeMillis();
		long r = ETIME-STIME;
		LogUtil.println("Spending Time : "+r+" ms");
		STIME = System.currentTimeMillis();
		return r;
	}
}
