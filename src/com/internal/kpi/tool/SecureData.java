package com.internal.kpi.tool;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;

public class SecureData {
	private String str = null;

	public SecureData(String str) {
		this.str = str;
	}

	public String secureAndVerifyString() throws Exception {
		return Base64.getEncoder().encodeToString(str.getBytes());
	}
	
	public static String decodeEncodedString(String encodedString) {
		return new String(Base64.getDecoder().decode(encodedString));
	}
	
	public static String hashPassword(String pass) throws NoSuchAlgorithmException {
		return DigestUtils.sha256Hex(pass);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
