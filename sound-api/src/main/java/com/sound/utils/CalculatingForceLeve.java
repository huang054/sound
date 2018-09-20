package com.sound.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.validator.cfg.context.ReturnValueConstraintMappingContext;

public class CalculatingForceLeve {

	public static int changLevel(BigDecimal big) {
		if(big.doubleValue()>=100&&big.doubleValue()<300) {
			return 1;
		}else if(big.doubleValue()>=300&&big.doubleValue()<500) {
			return 2;
		}else if(big.doubleValue()>=500&&big.doubleValue()<1000) {
			return 3;
		}else if(big.doubleValue()>=1000&&big.doubleValue()<2000) {
			return 4;
		}else if(big.doubleValue()>=2000&&big.doubleValue()<6000) {
			return 5;
		}else if(big.doubleValue()>=6000&&big.doubleValue()<12000) {
			return 6;
		}else if(big.doubleValue()>=12000&&big.doubleValue()<30000) {
			return 7;
		}else if(big.doubleValue()>=30000&&big.doubleValue()<50000) {
			return 8;
		}else if(big.doubleValue()>=50000) {
			return 9;
		}
		return 0;
	}
	
	public static String lableByCalculating(BigDecimal big) {
		if(big.intValue()<100) {
			return Lable.SPACERESIDENT.getMsg();
		}else if(big.intValue()<300) {
			return Lable.NEPTUNERESIDENT.getMsg();
		}else if(big.intValue()<500) {
			return Lable.URANUSRESIDENT.getMsg();
		}else if(big.intValue()<1000){
			return Lable.SATURNRESIDENT.getMsg();
		}else if(big.intValue()<2000){
			return Lable.JUPEITERRESIDENT.getMsg();
		}else if(big.intValue()<6000){
			return Lable.SPARKRESIDENT.getMsg();
		}else if(big.intValue()<12000){
			return Lable.EARTHRESIDENT.getMsg();
		}else if(big.intValue()<30000){
			return Lable.VENUSRESIDENT.getMsg();
		}else if(big.intValue()<50000){
			return Lable.MERCURYRESIDENT.getMsg();
		}else {
			return Lable.SUNRESIDENT.getMsg();
		}
		
	}
	
	public static String lable(BigDecimal big) {
		if(big.intValue()<500) {
			return Miner.PRIMARY.getMsg();
		}else if(big.intValue()<5000) {
			return Miner.MIDDEL.getMsg();
		}else if(big.intValue()<15000) {
			return Miner.HIGHT.getMsg();
		}else {
			return Miner.SUPERFINE.getMsg();
		}
	}
	
	public static String lableByBirthday(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String s = format.format(date);
		int month=Integer.parseInt(s.split("-")[1]);
		int day=Integer.parseInt(s.split("-")[2]);
		if((month==3&&day>=21)||(month==4&&day<=19)) {
			return Sign.ARIES.getMsg();
		}
		if((month==4&&day>=20)||(month==5&&day<=20)) {
			return Sign.TAURUS.getMsg();
		}
		if((month==5&&day>=21)||(month==6&&day<=21)) {
			return Sign.GEMINI.getMsg();
		}
		if((month==6&&day>=22)||(month==7&&day<=22)) {
			return Sign.CANCER.getMsg();
		}
		if((month==7&&day>=23)||(month==8&&day<=22)) {
			return Sign.LEO.getMsg();
		}
		if((month==8&&day>=23)||(month==9&&day<=22)) {
			return Sign.VIRGO.getMsg();
		}
		if((month==9&&day>=23)||(month==10&&day<=23)) {
			return Sign.LIBRA.getMsg();
		}
		if((month==10&&day>=24)||(month==11&&day<=21)) {
			return Sign.SCORPIUS.getMsg();
		}
		if((month==11&&day>=22)||(month==12&&day<=21)) {
			return Sign.SAGITTARIUS.getMsg();
		}
		if((month==12&&day>=22)||(month==1&&day<=19)) {
			return Sign.CAPRICORN.getMsg();
		}
		if((month==1&&day>=20)||(month==2&&day<=18)) {
			return Sign.AQUARIUS.getMsg();
		}
		if((month==2&&day>=19)||(month==3&&day<=20)) {
			return Sign.PISCES.getMsg();
		}
		
		
		return "星座标签出错";
	}

}

