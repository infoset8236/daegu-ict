package kr.co.sj.framework.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class CalculateHashUtils {


	// UTF-8 → SHA-256 → hex(소문자)
	public static String sha256HexUtf8(String s) {
		try {
			byte[] d = MessageDigest.getInstance("SHA-256")
					.digest(s.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder(d.length * 2);
			for (byte b : d) sb.append(String.format("%02x", b));
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String calculateHashAndUTF8(String message) {
		MessageDigest sha256 = null;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// UTF-8로 인코딩해서 해시 → Base64 →(그 바이트를) 16진수로 변환
		byte[] hash = Base64.encodeBase64(sha256.digest(message.getBytes(StandardCharsets.UTF_8)));

		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String returnFormatter = formatter.toString();

		formatter.close();
		return returnFormatter;
	}

	public static String calculateHash(String message) {
		MessageDigest sha256 = null;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		sha1.update(message.getBytes());
//        byte[] hash = sha1.digest();
        byte[] hash = Base64.encodeBase64(sha256.digest(message.getBytes()));

        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String returnFormatter = formatter.toString();

        formatter.close();
        return returnFormatter;
    }

	public static String calculateHashSHA256(String message) {
		MessageDigest sha256 = null;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] hash = sha256.digest(message.getBytes());

		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02X", b);
		}
		String returnFormatter = formatter.toString();

		formatter.close();
		return returnFormatter;
	}
}
