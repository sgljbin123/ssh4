/*     */ package com.utils;
/*     */ 
/*     */ /*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.InvalidAlgorithmParameterException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import java.util.Arrays;

/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.SecretKeyFactory;
/*     */ import javax.crypto.spec.DESedeKeySpec;
/*     */ import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ public class DesUtil
/*     */ {
/*     */   public static final String CBC_MODEL = "CBC";
/*     */   public static final String ECB_MODEL = "ECB";
/*     */   public static final String NOPADDING = "NoPadding";
/*     */   public static final String PKCS5PADDING = "PKCS5Padding";
/*  36 */   private static final byte[] DES_IV = new byte[8];
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/*  47 */     String str = "597818";
/*  48 */     byte[] encrypt = tDesEncrypt(str.getBytes("UTF-8"), ConstantUtil.DES_KEY, "ECB", "NoPadding");
/*     */ 
/*  50 */     System.out.println(bytesToHexString(encrypt));
/*  51 */     String source = tDesDecrypt(hexStr2ByteArray(bytesToHexString(encrypt)), ConstantUtil.DES_KEY, "ECB", "NoPadding");
/*  52 */     System.out.println("明文： " + source);
/*     */   }
/*     */ 
/*     */   public static String bytesToHexString(byte[] src)
/*     */   {
/*  61 */     StringBuilder stringBuilder = new StringBuilder("");
/*  62 */     if ((src == null) || (src.length <= 0)) {
/*  63 */       return null;
/*     */     }
/*  65 */     for (int i = 0; i < src.length; i++) {
/*  66 */       int v = src[i] & 0xFF;
/*  67 */       String hv = Integer.toHexString(v);
/*  68 */       if (hv.length() < 2) {
/*  69 */         stringBuilder.append(0);
/*     */       }
/*  71 */       stringBuilder.append(hv);
/*     */     }
/*  73 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static String byte2hex(byte[] b)
/*     */   {
/*  81 */     String hs = "";
/*  82 */     String stmp = "";
/*     */ 
/*  84 */     for (int n = 0; n < b.length; n++) {
/*  85 */       stmp = Integer.toHexString(b[n] & 0xFF);
/*  86 */       if (stmp.length() == 1) hs = hs + "0" + stmp; else
/*  87 */         hs = hs + stmp;
/*  88 */       if (n < b.length - 1) hs = hs + ":";
/*     */     }
/*  90 */     return hs.toUpperCase();
/*     */   }
/*     */ 
/*     */   public static byte[] hexStr2ByteArray(String hexString)
/*     */   {
/* 101 */     if (StringUtils.isEmpty(hexString)) {
/* 102 */       throw new IllegalArgumentException("this hexString must not be empty");
/*     */     }
/* 104 */     hexString = hexString.toLowerCase();
/* 105 */     byte[] byteArray = new byte[hexString.length() / 2];
/* 106 */     int k = 0;
/* 107 */     for (int i = 0; i < byteArray.length; i++)
/*     */     {
/* 113 */       byte high = (byte)(Character.digit(hexString.charAt(k), 16) & 0xFF);
/* 114 */       byte low = (byte)(Character.digit(hexString.charAt(k + 1), 16) & 0xFF);
/* 115 */       byteArray[i] = ((byte)(high << 4 | low));
/* 116 */       k += 2;
/*     */     }
/* 118 */     return byteArray;
/*     */   }
/*     */ 
/*     */   public static byte[] tDesEncrypt(byte[] data, byte[] key, String model, String padding)
/*     */   {
/* 131 */     if (key.length != 16) {
/* 132 */       throw new GwiInfoSecurityException("密钥长度错误或密文长度错误");
/*     */     }
/* 134 */     String transformation = null;
/* 135 */     if (model.equals("CBC")) {
/* 136 */       if (padding.equals("NoPadding"))
/* 137 */         transformation = "DESede/CBC/NoPadding";
/* 138 */       else if (padding.equals("PKCS5Padding"))
/* 139 */         transformation = "DESede/CBC/PKCS5Padding";
/*     */       else {
/* 141 */         throw new GwiInfoSecurityException("填充模式错误");
/*     */       }
/* 143 */       return tDesEncrypt_CBC(data, key, transformation);
/* 144 */     }if (model.equals("ECB")) {
/* 145 */       if (padding.equals("NoPadding"))
/* 146 */         transformation = "DESede/ECB/NOPadding";
/* 147 */       else if (padding.equals("PKCS5Padding"))
/* 148 */         transformation = "DESede/ECB/PKCS5Padding";
/*     */       else {
/* 150 */         throw new GwiInfoSecurityException("填充模式错误");
/*     */       }
/* 152 */       return tDesEncrypt_ECB(data, key, transformation);
/*     */     }
/* 154 */     throw new GwiInfoSecurityException("加密模式错误");
/*     */   }
/*     */ 
/*     */   public static String tDesDecrypt(byte[] data, byte[] key, String model, String padding)
/*     */   {
/* 168 */     if ((key.length != 16) || (data.length % 8 != 0)) {
/* 169 */       throw new GwiInfoSecurityException("密钥长度错误或密文长度错误");
/*     */     }
/* 171 */     String transformation = null;
/* 172 */     if (model.equals("CBC")) {
/* 173 */       if (padding.equals("NoPadding"))
/* 174 */         transformation = "DESede/CBC/NoPadding";
/* 175 */       else if (padding.equals("PKCS5Padding"))
/* 176 */         transformation = "DESede/CBC/PKCS5Padding";
/*     */       else {
/* 178 */         throw new GwiInfoSecurityException("填充模式错误");
/*     */       }
/* 180 */       byte[] result = tDesDecrypt_CBC(data, key, transformation);
/* 181 */       String rst = null;
/*     */       try {
/* 183 */         rst = new String(result, "UTF-8");
/*     */       } catch (UnsupportedEncodingException e) {
/* 185 */         throw new GwiInfoSecurityException("结束转化错误");
/*     */       }
/* 187 */       return rst;
/* 188 */     }if (model.equals("ECB")) {
/* 189 */       if (padding.equals("NoPadding"))
/* 190 */         transformation = "DESede/ECB/NOPadding";
/* 191 */       else if (padding.equals("PKCS5Padding"))
/* 192 */         transformation = "DESede/ECB/PKCS5Padding";
/*     */       else {
/* 194 */         throw new GwiInfoSecurityException("填充模式错误");
/*     */       }
/* 196 */       byte[] result = tDesDecrypt_ECB(data, key, transformation);
/* 197 */       String rst = null;
/*     */       try {
/* 199 */         rst = new String(result, "UTF-8");
/*     */       } catch (UnsupportedEncodingException e) {
/* 201 */         throw new GwiInfoSecurityException("结束转化错误");
/*     */       }
/* 203 */       return rst;
/*     */     }
/* 205 */     throw new GwiInfoSecurityException("加密模式错误");
/*     */   }
/*     */ 
/*     */   private static byte[] tDesEncrypt_ECB(byte[] data, byte[] key, String transformation)
/*     */   {
/* 218 */     byte[] deskey = new byte[24];
/* 219 */     for (int i = 0; i < key.length; i++)
/* 220 */       deskey[i] = key[i];
/* 221 */     for (int j = 0; j < 8; j++) {
/* 222 */       deskey[(j + 16)] = key[j];
/*     */     }
/* 224 */     if (transformation.equals("DESede/ECB/NOPadding"))
/*     */     {
/*     */       byte[] enData;
/* 226 */       if (data.length % 8 == 0) {
/* 227 */         enData = new byte[data.length + 8];
/* 228 */         int i = 0;
/* 229 */         for (i = 0; i < data.length; i++) {
/* 230 */           enData[i] = data[i];
/*     */         }
/* 232 */         enData[i] = -128;
/*     */       } else {
/* 234 */         int len = data.length / 8;
/* 235 */         len = (len + 1) * 8;
/* 236 */         enData = new byte[len];
/* 237 */         int i = 0;
/* 238 */         for (i = 0; i < data.length; i++) {
/* 239 */           enData[i] = data[i];
/*     */         }
/* 241 */         enData[i] = -128;
/*     */       }
/* 243 */       data = enData;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 248 */       Cipher cipher = Cipher.getInstance(transformation);
/* 249 */       SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
/* 250 */       SecretKey sk = skf.generateSecret(new DESedeKeySpec(deskey));
/* 251 */       cipher.init(1, sk);
/* 252 */       return cipher.doFinal(data);
/*     */     }
/*     */     catch (NoSuchAlgorithmException e) {
/* 255 */       throw new GwiInfoSecurityException("没有这种算法名称");
/*     */     }
/*     */     catch (NoSuchPaddingException e) {
/* 258 */       throw new GwiInfoSecurityException("没有这种填充方式");
/*     */     }
/*     */     catch (InvalidKeyException e) {
/* 261 */       throw new GwiInfoSecurityException("密钥错误");
/*     */     }
/*     */     catch (InvalidKeySpecException e) {
/* 264 */       throw new GwiInfoSecurityException("密钥方式错误");
/*     */     }
/*     */     catch (IllegalBlockSizeException e) {
/* 267 */       throw new GwiInfoSecurityException("错误的密文");
/*     */     } catch (BadPaddingException e) {
/*     */     }
/* 270 */     throw new GwiInfoSecurityException("错误的填充");
/*     */   }
/*     */ 
/*     */   private static byte[] tDesDecrypt_ECB(byte[] data, byte[] key, String transformation)
/*     */   {
/* 283 */     if ((key.length != 16) || (data.length % 8 != 0)) {
/* 284 */       return null;
/*     */     }
/* 286 */     byte[] deskey = new byte[24];
/* 287 */     for (int i = 0; i < key.length; i++)
/* 288 */       deskey[i] = key[i];
/* 289 */     for (int j = 0; j < 8; j++) {
/* 290 */       deskey[(j + 16)] = key[j];
/*     */     }
/*     */     try
/*     */     {
/* 294 */       Cipher cipher = Cipher.getInstance(transformation);
/* 295 */       SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
/* 296 */       SecretKey sk = skf.generateSecret(new DESedeKeySpec(deskey));
/* 297 */       cipher.init(2, sk);
/* 298 */       byte[] cipherData = cipher.doFinal(data);
/*     */ 
/* 301 */       if (transformation.equals("DESede/ECB/NOPadding")) {
/* 302 */         int count = 1;
/* 303 */         for (int j = cipherData.length; j > 0; j--) {
/* 304 */           if (cipherData[(j - 1)] == -128) {
/*     */             break;
/*     */           }
/* 307 */           count++;
/*     */         }
/* 309 */         return Arrays.copyOfRange(cipherData, 0, cipherData.length - count);
/*     */       }
/* 311 */       return cipherData;
/*     */     }
/*     */     catch (NoSuchAlgorithmException e) {
/* 314 */       throw new GwiInfoSecurityException("没有这种算法名称");
/*     */     }
/*     */     catch (NoSuchPaddingException e) {
/* 317 */       throw new GwiInfoSecurityException("没有这种填充方式");
/*     */     }
/*     */     catch (InvalidKeyException e) {
/* 320 */       throw new GwiInfoSecurityException("密钥错误");
/*     */     }
/*     */     catch (InvalidKeySpecException e) {
/* 323 */       throw new GwiInfoSecurityException("密钥方式错误");
/*     */     }
/*     */     catch (IllegalBlockSizeException e) {
/* 326 */       throw new GwiInfoSecurityException("错误的密文");
/*     */     } catch (BadPaddingException e) {
/*     */     }
/* 329 */     throw new GwiInfoSecurityException("错误的填充");
/*     */   }
/*     */ 
/*     */   private static byte[] tDesEncrypt_CBC(byte[] data, byte[] key, String transformation)
/*     */   {
/* 342 */     byte[] deskey = new byte[24];
/* 343 */     for (int i = 0; i < key.length; i++)
/* 344 */       deskey[i] = key[i];
/* 345 */     for (int j = 0; j < 8; j++) {
/* 346 */       deskey[(j + 16)] = key[j];
/*     */     }
/* 348 */     if (transformation.equals("DESede/CBC/NoPadding"))
/*     */     {
/*     */       byte[] enData;
/* 350 */       if (data.length % 8 == 0) {
/* 351 */         enData = new byte[data.length + 8];
/* 352 */         int i = 0;
/* 353 */         for (i = 0; i < data.length; i++)
/* 354 */           enData[i] = data[i];
/* 355 */         enData[i] = -128;
/*     */       } else {
/* 357 */         int len = data.length / 8;
/* 358 */         len = (len + 1) * 8;
/* 359 */         enData = new byte[len];
/* 360 */         int i = 0;
/* 361 */         for (i = 0; i < data.length; i++)
/* 362 */           enData[i] = data[i];
/* 363 */         enData[i] = -128;
/*     */       }
/* 365 */       data = enData;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 370 */       Cipher cipher = Cipher.getInstance(transformation);
/*     */ 
/* 372 */       SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
/* 373 */       SecretKey sk = skf.generateSecret(new DESedeKeySpec(deskey));
/* 374 */       IvParameterSpec ips = new IvParameterSpec(DES_IV);
/* 375 */       cipher.init(1, sk, ips);
/* 376 */       return cipher.doFinal(data);
/*     */     }
/*     */     catch (NoSuchAlgorithmException e) {
/* 379 */       throw new GwiInfoSecurityException("没有这种算法名称");
/*     */     }
/*     */     catch (NoSuchPaddingException e) {
/* 382 */       throw new GwiInfoSecurityException("没有这种填充方式");
/*     */     }
/*     */     catch (InvalidKeyException e) {
/* 385 */       throw new GwiInfoSecurityException("密钥错误");
/*     */     }
/*     */     catch (InvalidKeySpecException e) {
/* 388 */       throw new GwiInfoSecurityException("密钥方式错误");
/*     */     }
/*     */     catch (IllegalBlockSizeException e) {
/* 391 */       throw new GwiInfoSecurityException("错误的密文");
/*     */     }
/*     */     catch (BadPaddingException e) {
/* 394 */       throw new GwiInfoSecurityException("错误的填充");
/*     */     } catch (InvalidAlgorithmParameterException e) {
/*     */     }
/* 397 */     throw new GwiInfoSecurityException("算法参数错误");
/*     */   }
/*     */ 
/*     */   private static byte[] tDesDecrypt_CBC(byte[] data, byte[] key, String transformation)
/*     */   {
/* 410 */     byte[] deskey = new byte[24];
/* 411 */     for (int i = 0; i < key.length; i++)
/* 412 */       deskey[i] = key[i];
/* 413 */     for (int j = 0; j < 8; j++) {
/* 414 */       deskey[(j + 16)] = key[j];
/*     */     }
/*     */     try
/*     */     {
/* 418 */       Cipher cipher = Cipher.getInstance(transformation);
/* 419 */       SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
/* 420 */       SecretKey sk = skf.generateSecret(new DESedeKeySpec(deskey));
/* 421 */       IvParameterSpec ips = new IvParameterSpec(DES_IV);
/* 422 */       cipher.init(2, sk, ips);
/* 423 */       byte[] cipherData = cipher.doFinal(data);
/*     */ 
/* 426 */       if (transformation.equals("DESede/CBC/NoPadding")) {
/* 427 */         int count = 1;
/* 428 */         for (int j = cipherData.length; j > 0; j--) {
/* 429 */           if (cipherData[(j - 1)] == -128) {
/*     */             break;
/*     */           }
/* 432 */           count++;
/*     */         }
/* 434 */         return Arrays.copyOfRange(cipherData, 0, cipherData.length - count);
/*     */       }
/* 436 */       return cipherData;
/*     */     }
/*     */     catch (NoSuchAlgorithmException e) {
/* 439 */       throw new GwiInfoSecurityException("没有这种算法名称");
/*     */     }
/*     */     catch (NoSuchPaddingException e) {
/* 442 */       throw new GwiInfoSecurityException("没有这种填充方式");
/*     */     }
/*     */     catch (InvalidKeyException e) {
/* 445 */       throw new GwiInfoSecurityException("密钥错误");
/*     */     }
/*     */     catch (InvalidKeySpecException e) {
/* 448 */       throw new GwiInfoSecurityException("密钥方式错误");
/*     */     }
/*     */     catch (IllegalBlockSizeException e) {
/* 451 */       throw new GwiInfoSecurityException("错误的密文");
/*     */     }
/*     */     catch (BadPaddingException e) {
/* 454 */       throw new GwiInfoSecurityException("错误的填充");
/*     */     } catch (InvalidAlgorithmParameterException e) {
/*     */     }
/* 457 */     throw new GwiInfoSecurityException("算法参数错误");
/*     */   }
/*     */ }

/* Location:           D:\svn\部门\06.运维_YW\02.运维组\11.系统版本\V2.0.2.2\interface\WebRoot\WEB-INF\classes\
 * Qualified Name:     com.agp.itf.util.DesUtil
 * JD-Core Version:    0.6.2
 */