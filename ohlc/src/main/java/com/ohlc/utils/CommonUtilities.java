package com.ohlc.utils;

import java.util.Calendar;
import java.util.Date;

public class CommonUtilities {

	public static Date convertTime(long time){
	    Date date = new Date(time);
	    return date;
	}
	
    public static Date addTimeBySeconds(Date date,int sec){
        try {
        	Calendar calender = Calendar.getInstance();
            calender.setTimeInMillis(date.getTime());
            calender.add(Calendar.SECOND, sec);
            Date changeDate=calender.getTime();
            return changeDate;
        } catch(Exception e) {
        	return null;
        }
    }
}
