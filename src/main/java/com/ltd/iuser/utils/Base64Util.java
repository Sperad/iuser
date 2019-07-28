package com.ltd.iuser.utils;

import java.util.Base64;

public abstract class Base64Util {

	public static String encode(String data) {
		return Base64.getEncoder().encodeToString(data.getBytes());
	}

	public static byte[] encode(byte[] data) {
		return Base64.getEncoder().encode(data);
	}

	public static String encodeToString(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	public static String decode(String data) {
		return new String(Base64.getDecoder().decode(data));
	}

	public static byte[] decode(byte[] data) {
		return Base64.getDecoder().decode(data);
	}
}
