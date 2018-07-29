package com.apache.cxf.spring.hibernate.util;

import java.util.Base64;


public class PasswordHandler {

	private static PasswordHandler instance = new PasswordHandler();

	public static PasswordHandler getInstance() {
		return PasswordHandler.instance;
	 }

	public String encrypt(String aData) {
		String result = "";
		try {
			byte[] encryptedData = Base64.getEncoder().encode(aData.getBytes());
			result = new String(encryptedData);
		} catch (Exception oException) {
			oException.getMessage();
		}
		 //System.out.println(result);
		return result;
	}

	public String decrypt(String aData) {
		String result = "";
		try {
			byte[] decodedData = Base64.getDecoder().decode(aData.getBytes());
			result = new String(decodedData);
		} catch (Exception oException) {
			oException.printStackTrace();
		}
		return result;
	}
	
	public static void main(final String[] args) {
		String encryptedPassword = PasswordHandler.getInstance().encrypt("survenka");
		String decryptedPassword = PasswordHandler.getInstance().decrypt("zqZioSbF/IpyjVP3DBebWA==");
		System.out.println(decryptedPassword);
	}
}
