package com.internal.kpi.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyData {
	
	/*
	 * Verify Email, if valid returns true
	 */
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}
	/*
	 * Verify string, if valid returns true
	 */
	public static boolean isValidString(String str) {
		String regx = "^[a-zA-Z ]+$";
	    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(str);
	    return matcher.find();
	}
	/*
	 * Verify password, if valid returns true
	 */
	public static boolean isValidPassword(String password) {
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
	    Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
	}
	/*
	 * Verify text, if valid returns true
	 */
	public static boolean isValidText(String text) {
		String regex = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$";
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
	}

}
