package com.lostfoundserver.utils;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;


public class Encrypt {
	public static String md5Encrypt(String s) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance("MD5");

		Base64 base64Encoder = new Base64();

		return base64Encoder.encodeToString(md5.digest(s.getBytes("utf-8")));
	}
}
	