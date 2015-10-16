package com.ty.photography.common;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
/**
 * des加密解密
 * @author liyan
 *
 */
public class DESUtil {
	private static String strDefaultKey = "?ty-@";
	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public DESUtil() throws Exception {
		this(strDefaultKey);
	}

	private DESUtil(String strKey) throws Exception {
		SecureRandom sr = new SecureRandom();  
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key, sr);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key, sr);
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	private Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}
	
    public Map<String, String> getParamMap(String param) throws Exception {
		Map<String, String> returnMap = new HashMap<String, String>();
		if (param != null && !param.equals("")) {
			String paramStr = decrypt(param);
			String[] params = paramStr.split("&"); 
			for (int i = 0; i < params.length; i++) {
				if(params[i]!=null && params[i].length()>0){
					String[] keyValue = params[i].split("=");
					returnMap.put(keyValue[0], keyValue[1]);
				}
			}
		}
		return returnMap;
	}
	
	public static void main(String[] args) throws Exception{
		DESUtil des = new DESUtil();
		String a =des.encrypt("123456");
		System.out.println(a);
		System.out.println(des.decrypt(a));
	}
}
