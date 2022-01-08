package com.internal.kpi.tool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import de.taimos.totp.TOTP;


public class SecondFactorAuthenticator {

	/*
	 * Generate secrete key which should be provided to google authenticator
	 */
	public static String generateSecretKey() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[20];
		random.nextBytes(bytes);
		Base32 base32 = new Base32();
		return base32.encodeToString(bytes);
	}

	/*
	 * Get TOTP code - converts base32 encoded secret keys to hex and uses the TOTP
	 * to turn them into 6-digits codes based on the current time.
	 */
	public static String getTOTPCode(String secretKey) {
		Base32 base32 = new Base32();
		byte[] bytes = base32.decode(secretKey);
		String hexKey = Hex.encodeHexString(bytes);
		return TOTP.getOTP(hexKey);
	}

	/*
	 * Converts base32 encoded secret keys to hex and uses the TOTP to turn them
	 * into 6-digits codes based on the current time.
	 */
	public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
		try {
			return "otpauth://totp/" + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
					+ "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20") + "&issuer="
					+ URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/*
	 * Create QR code for google authenticator
	 */
	public static void createQRCode(String barCodeData, String filePath, int height, int width)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE, width, height);
		try (FileOutputStream out = new FileOutputStream(filePath)) {
			MatrixToImageWriter.writeToStream(matrix, "png", out);
		}
	}
}
