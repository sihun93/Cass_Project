package com.work.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/** 
 * <pre>
 * 객체 생성없이 사용하기 위한 공통기능 유틸클래스
 * </pre>
 * 
 * @author 김종호
 * @version ver.1.0
 * @since jdk.1.8
 */
public class Utility {

	/**
	 * 현재날짜 반환 
	 * @return 현재 기본형식(년도4-월2-일2) 날짜 
	 */
	public static String getCurrentDate() {
		return getCurrentDate("yyyy-MM-dd", Locale.KOREA);
	}
	
	public static String getCurrentDate(String pattern) {
		return getCurrentDate(pattern, Locale.KOREA);
	}
	
	public static String getCurrentDate(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	/**
	 * <pre>
	 * 보안문자 숫자형식 반환
	 * </pre>
	 * @return 보안문자 숫자6자리 반환
	 */
	public static String getSecureNumber() {
		return getSecureNumber(6);
	}
	
	public static String getSecureNumber(int length) {
		StringBuilder secureNumber = new StringBuilder();
		Random random = new Random((long)(System.nanoTime() * Math.random()));
		for (int index = 0; index < length; index++) {
			secureNumber.append(random.nextInt(10));
		}
		return secureNumber.toString();
	}
	
	/**
	 * <pre>
	 * 보안문자 숫자+영문조합 반환
	 * </pre>
	 * @return 기본 숫자+영문조합 6자리 
	 */
	public static String getSecureNumberAndString() {
		return getSecureNumberAndString(8, true);
	}
	
	public static String getSecureNumberAndString(int length) {
		return getSecureNumberAndString(length, true);
	}

	/**
	 * <pre>
	 * 보안문자 숫자+영문조합 반환
	 * </pre>
	 * @param length 길이
	 * @param isUpper 보안영문 대소문자 
	 * @return 지정한 길의 영문대소문자+숫자결합 
	 */
	public static String getSecureNumberAndString(int length, boolean isUpper) {
		StringBuilder secureNumber = new StringBuilder();
		Random random = new Random((long)(System.nanoTime() * Math.random()));
		for (int index = 0; index < length; index++) {
			if (random.nextBoolean()) {
				secureNumber.append(random.nextInt(10));
			} else {
				if (isUpper) {
					secureNumber.append((char)(random.nextInt(26) + 65));
				} else {
					secureNumber.append((char)(random.nextInt(26) + 97));
				}
			}
		}
		return secureNumber.toString();
	}	
	
	
	/**
	 * 포인트 숫자만
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		boolean result = true;
		// null, 공백일시
		if (str == null || str.length() == 0) {
			result = false;
		}
		// null이나 공백이 아닐시
		else {
			for (int i = 0; i < str.length(); i++) {
				int c = (int) str.charAt(i);
				// 숫자가 아니라면
				if (c < 48 || c > 57) {
					result = false;
				}
			}
		}
		return result;
	}
	
	/** 마일리지에 콤마(,)표기 */
	public static String convertNumberToString(long no) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		return numberFormat.format(no);
	}
	
	public static void main(String[] args) {
		System.out.println("\n현재날짜");
		System.out.println(Utility.getCurrentDate());
		System.out.println(Utility.getCurrentDate("yyyy.MM.dd"));
		System.out.println(Utility.getCurrentDate("MM.dd"));
		System.out.println(Utility.getCurrentDate("HH:mm:ss"));
		System.out.println(Utility.getCurrentDate("[a]hh:mm:ss"));
		System.out.println(Utility.getCurrentDate("[a]hh:mm:ss", Locale.US));
		System.out.println(Utility.getCurrentDate("[a]hh:mm:ss", Locale.CHINA));
		
		System.out.println("\n보안문자 : 숫자형식");
		System.out.println(Utility.getSecureNumber());
		System.out.println(Utility.getSecureNumber(4));
		System.out.println(Utility.getSecureNumber(8));
		System.out.println(Utility.getSecureNumber(12));
		
		System.out.println("\n보안문자 : 숫자 + 대문자");
		System.out.println(Utility.getSecureNumberAndString());
		System.out.println(Utility.getSecureNumberAndString(4));
		System.out.println(Utility.getSecureNumberAndString(10));
		
		System.out.println("\n보안문자 : 숫자 + 대.소문자");
		System.out.println(Utility.getSecureNumberAndString(10, true));
		System.out.println(Utility.getSecureNumberAndString(12, false));
	}
}
