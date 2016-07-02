package com.utils;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;


public class EncryptAndDecrypt {

	private static Logger logger = Logger.getLogger(EncryptAndDecrypt.class);
	
	public static  String encrypt(String str){
		byte[] encrypt = null;
		logger.info(str);
		try {
			encrypt = DesUtil.tDesEncrypt(str.getBytes("UTF-8"), ConstantUtil.DES_KEY, "ECB", "NoPadding");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DesUtil.bytesToHexString(encrypt);
	}
	
	public String Decrypt(String encrypt){
		return DesUtil.tDesDecrypt(DesUtil.hexStr2ByteArray(encrypt), ConstantUtil.DES_KEY, "ECB", "NoPadding");
	}
}
