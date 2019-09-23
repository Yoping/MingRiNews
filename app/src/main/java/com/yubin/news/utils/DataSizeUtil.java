package com.yubin.news.utils;

import java.text.DecimalFormat;

/**
 * @author ：Yoping
 * @time：2016年1月30日 下午4:33:52
 */
public class DataSizeUtil {

	public static String formatSizeFromByte(long size){
		DecimalFormat fomater=new DecimalFormat("####.00");
		if(size<1024){
			return size+"bytes";
		}else if(size<1024*1024){
			float kbSize=size/1024f;
			return fomater.format(kbSize)+"KB";
		}else if(size<1024*1024*1024){
			float mbSize=size/1024f/1024f;
			return fomater.format(mbSize)+"MB";
		}else if(size<1024*1024*1024*1024){
			float gbSize=size/1024f/1024f/1024f;
			return fomater.format(gbSize)+"GB";
		}else{
			return "error size";
		}
	}
	
	public static String formatSizeFromKB(int sizeKB){
		Long size=(long) (sizeKB*1024);
		return formatSizeFromByte(size);
	}
}
