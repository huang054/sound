package com.sound.utils;

public class Rank {
   
	public static String rank(long count) {
		   long i=count+1;
        	if(i<100&&count>=10) {
        		
        		return "0"+i;
        	}
        	if(count<10) {
    			return "00"+i;
    		}
        	return String.valueOf(i);
       
	}
}
