package utils;

import java.util.UUID;

public class Checker {
	public static void main(String[] args) throws Exception {
		// 创建加解密
		AESencrp aes = new AESencrp();
		// 设置加解密算法
		aes.setALGO("AES");
		// 设置加解密密钥
		String key = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
		System.out.println(key);
		aes.setKeyValue(key.getBytes());
		// 要进行加密的密码
		String password = "cat123@#steven";
		// 进行加密后的字符串
		String passwordEnc = aes.encrypt(password);
		String passwordDec = aes.decrypt(passwordEnc);
		System.out.println("原来的密码 : " + password);
		System.out.println("加密后的密码 : " + passwordEnc);
		System.out.println("解密后的原密码 : " + passwordDec);
	}
}
