package com.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;

import java.util.Arrays;

import javax.crypto.BadPaddingException;

import javax.crypto.Cipher;

import javax.crypto.IllegalBlockSizeException;

import javax.crypto.NoSuchPaddingException;

import javax.crypto.SecretKey;

import javax.crypto.SecretKeyFactory;

import javax.crypto.spec.DESedeKeySpec;

import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.lang3.StringUtils;

public class DesUtil {
	public static final String CBC_MODEL = "CBC";
	public static final String ECB_MODEL = "ECB";
	public static final String NOPADDING = "NoPadding";
	public static final String PKCS5PADDING = "PKCS5Padding";
	private static final byte[] DES_IV = new byte[8];

	public static void main(String[] args) throws Exception {
		String str = "597818";
		byte[] encrypt = tDesEncrypt(str.getBytes("UTF-8"),
				ConstantUtil.DES_KEY, "ECB", "NoPadding");

		System.out.println(bytesToHexString(encrypt));
		String source = tDesDecrypt(
				hexStr2ByteArray(bytesToHexString(encrypt)),
				ConstantUtil.DES_KEY, "ECB", "NoPadding");
		System.out.println("明文： " + source);
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if ((src == null) || (src.length <= 0)) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	public static byte[] hexStr2ByteArray(String hexString) {
		if (StringUtils.isEmpty(hexString)) {
			throw new IllegalArgumentException(
					"this hexString must not be empty");
		}
		hexString = hexString.toLowerCase();
		byte[] byteArray = new byte[hexString.length() / 2];
		int k = 0;
		for (int i = 0; i < byteArray.length; i++) {
			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xFF);
			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xFF);
			byteArray[i] = ((byte) (high << 4 | low));
			k += 2;
		}
		return byteArray;
	}

	public static byte[] tDesEncrypt(byte[] data, byte[] key, String model,
			String padding) {
		if (key.length != 16) {
			throw new GwiInfoSecurityException("密钥长度错误或密文长度错误");
		}
		String transformation = null;
		if (model.equals("CBC")) {
			if (padding.equals("NoPadding"))
				transformation = "DESede/CBC/NoPadding";
			else if (padding.equals("PKCS5Padding"))
				transformation = "DESede/CBC/PKCS5Padding";
			else {
				throw new GwiInfoSecurityException("填充模式错误");
			}
			return tDesEncrypt_CBC(data, key, transformation);
		}
		if (model.equals("ECB")) {
			if (padding.equals("NoPadding"))
				transformation = "DESede/ECB/NOPadding";
			else if (padding.equals("PKCS5Padding"))
				transformation = "DESede/ECB/PKCS5Padding";
			else {
				throw new GwiInfoSecurityException("填充模式错误");
			}
			return tDesEncrypt_ECB(data, key, transformation);
		}
		throw new GwiInfoSecurityException("加密模式错误");
	}

	public static String tDesDecrypt(byte[] data, byte[] key, String model,
			String padding) {
		if ((key.length != 16) || (data.length % 8 != 0)) {
			throw new GwiInfoSecurityException("密钥长度错误或密文长度错误");
		}
		String transformation = null;
		if (model.equals("CBC")) {
			if (padding.equals("NoPadding"))
				transformation = "DESede/CBC/NoPadding";
			else if (padding.equals("PKCS5Padding"))
				transformation = "DESede/CBC/PKCS5Padding";
			else {
				throw new GwiInfoSecurityException("填充模式错误");
			}
			byte[] result = tDesDecrypt_CBC(data, key, transformation);
			String rst = null;
			try {
				rst = new String(result, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new GwiInfoSecurityException("结束转化错误");
			}
			return rst;
		}
		if (model.equals("ECB")) {
			if (padding.equals("NoPadding"))
				transformation = "DESede/ECB/NOPadding";
			else if (padding.equals("PKCS5Padding"))
				transformation = "DESede/ECB/PKCS5Padding";
			else {
				throw new GwiInfoSecurityException("填充模式错误");
			}
			byte[] result = tDesDecrypt_ECB(data, key, transformation);
			String rst = null;
			try {
				rst = new String(result, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new GwiInfoSecurityException("结束转化错误");
			}
			return rst;
		}
		throw new GwiInfoSecurityException("加密模式错误");
	}

	private static byte[] tDesEncrypt_ECB(byte[] data, byte[] key,
			String transformation) {
		byte[] deskey = new byte[24];
		for (int i = 0; i < key.length; i++)
			deskey[i] = key[i];
		for (int j = 0; j < 8; j++) {
			deskey[(j + 16)] = key[j];
		}
		if (transformation.equals("DESede/ECB/NOPadding")) {
			byte[] enData;
			if (data.length % 8 == 0) {
				enData = new byte[data.length + 8];
				int i = 0;
				for (i = 0; i < data.length; i++) {
					enData[i] = data[i];
				}
				enData[i] = -128;
			} else {
				int len = data.length / 8;
				len = (len + 1) * 8;
				enData = new byte[len];
				int i = 0;
				for (i = 0; i < data.length; i++) {
					enData[i] = data[i];
				}
				enData[i] = -128;
			}
			data = enData;
		}

		try {
			Cipher cipher = Cipher.getInstance(transformation);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			SecretKey sk = skf.generateSecret(new DESedeKeySpec(deskey));
			cipher.init(1, sk);
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			throw new GwiInfoSecurityException("没有这种算法名称");
		} catch (NoSuchPaddingException e) {
			throw new GwiInfoSecurityException("没有这种填充方式");
		} catch (InvalidKeyException e) {
			throw new GwiInfoSecurityException("密钥错误");
		} catch (InvalidKeySpecException e) {
			throw new GwiInfoSecurityException("密钥方式错误");
		} catch (IllegalBlockSizeException e) {
			throw new GwiInfoSecurityException("错误的密文");
		} catch (BadPaddingException e) {
		}
		throw new GwiInfoSecurityException("错误的填充");
	}

	private static byte[] tDesDecrypt_ECB(byte[] data, byte[] key,
			String transformation) {
		if ((key.length != 16) || (data.length % 8 != 0)) {
			return null;
		}
		byte[] deskey = new byte[24];
		for (int i = 0; i < key.length; i++)
			deskey[i] = key[i];
		for (int j = 0; j < 8; j++) {
			deskey[(j + 16)] = key[j];
		}
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			SecretKey sk = skf.generateSecret(new DESedeKeySpec(deskey));
			cipher.init(2, sk);
			byte[] cipherData = cipher.doFinal(data);

			if (transformation.equals("DESede/ECB/NOPadding")) {
				int count = 1;
				for (int j = cipherData.length; j > 0; j--) {
					if (cipherData[(j - 1)] == -128) {
						break;
					}
					count++;
				}
				return Arrays.copyOfRange(cipherData, 0, cipherData.length
						- count);
			}
			return cipherData;
		} catch (NoSuchAlgorithmException e) {
			throw new GwiInfoSecurityException("没有这种算法名称");
		} catch (NoSuchPaddingException e) {
			throw new GwiInfoSecurityException("没有这种填充方式");
		} catch (InvalidKeyException e) {
			throw new GwiInfoSecurityException("密钥错误");
		} catch (InvalidKeySpecException e) {
			throw new GwiInfoSecurityException("密钥方式错误");
		} catch (IllegalBlockSizeException e) {
			throw new GwiInfoSecurityException("错误的密文");
		} catch (BadPaddingException e) {
		}
		throw new GwiInfoSecurityException("错误的填充");
	}

	private static byte[] tDesEncrypt_CBC(byte[] data, byte[] key,
			String transformation) {
		byte[] deskey = new byte[24];
		for (int i = 0; i < key.length; i++)
			deskey[i] = key[i];
		for (int j = 0; j < 8; j++) {
			deskey[(j + 16)] = key[j];
		}
		if (transformation.equals("DESede/CBC/NoPadding")) {
			byte[] enData;
			if (data.length % 8 == 0) {
				enData = new byte[data.length + 8];
				int i = 0;
				for (i = 0; i < data.length; i++)
					enData[i] = data[i];
				enData[i] = -128;
			} else {
				int len = data.length / 8;
				len = (len + 1) * 8;
				enData = new byte[len];
				int i = 0;
				for (i = 0; i < data.length; i++)
					enData[i] = data[i];
				enData[i] = -128;
			}
			data = enData;
		}

		try {
			Cipher cipher = Cipher.getInstance(transformation);

			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			SecretKey sk = skf.generateSecret(new DESedeKeySpec(deskey));
			IvParameterSpec ips = new IvParameterSpec(DES_IV);
			cipher.init(1, sk, ips);
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			throw new GwiInfoSecurityException("没有这种算法名称");
		} catch (NoSuchPaddingException e) {
			throw new GwiInfoSecurityException("没有这种填充方式");
		} catch (InvalidKeyException e) {
			throw new GwiInfoSecurityException("密钥错误");
		} catch (InvalidKeySpecException e) {
			throw new GwiInfoSecurityException("密钥方式错误");
		} catch (IllegalBlockSizeException e) {
			throw new GwiInfoSecurityException("错误的密文");
		} catch (BadPaddingException e) {
			throw new GwiInfoSecurityException("错误的填充");
		} catch (InvalidAlgorithmParameterException e) {
		}
		throw new GwiInfoSecurityException("算法参数错误");
	}

	private static byte[] tDesDecrypt_CBC(byte[] data, byte[] key,
			String transformation) {
		byte[] deskey = new byte[24];
		for (int i = 0; i < key.length; i++)
			deskey[i] = key[i];
		for (int j = 0; j < 8; j++) {
			deskey[(j + 16)] = key[j];
		}
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
			SecretKey sk = skf.generateSecret(new DESedeKeySpec(deskey));
			IvParameterSpec ips = new IvParameterSpec(DES_IV);
			cipher.init(2, sk, ips);
			byte[] cipherData = cipher.doFinal(data);

			if (transformation.equals("DESede/CBC/NoPadding")) {
				int count = 1;
				for (int j = cipherData.length; j > 0; j--) {
					if (cipherData[(j - 1)] == -128) {
						break;
					}
					count++;
				}
				return Arrays.copyOfRange(cipherData, 0, cipherData.length
						- count);
			}
			return cipherData;
		} catch (NoSuchAlgorithmException e) {
			throw new GwiInfoSecurityException("没有这种算法名称");
		} catch (NoSuchPaddingException e) {
			throw new GwiInfoSecurityException("没有这种填充方式");
		} catch (InvalidKeyException e) {
			throw new GwiInfoSecurityException("密钥错误");
		} catch (InvalidKeySpecException e) {
			throw new GwiInfoSecurityException("密钥方式错误");
		} catch (IllegalBlockSizeException e) {
			throw new GwiInfoSecurityException("错误的密文");
		} catch (BadPaddingException e) {
			throw new GwiInfoSecurityException("错误的填充");
		} catch (InvalidAlgorithmParameterException e) {
		}
		throw new GwiInfoSecurityException("算法参数错误");
	}

}
