package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

/**
 * 
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class RSACoderTest {

	private String	publicKey	= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQ697301PJYhgM1b2o3AsWQRlPXpBbNtbFnXEA82S2Ozpa6qXGxopBzvUOs44eM6qSYGA5bXWcM0GPziosQ3OeEPeSy2HCQuFum380lDH0XQj3V7nPkf7lHe7flfsdJlzvU8IuCCFzFZJM1YQpmLoc/vRT91z0szS+XUL5bN2brQIDAQAB";
	private String	privateKey	= "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANDr3vfTU8liGAzVvajcCxZBGU9ekFs21sWdcQDzZLY7OlrqpcbGikHO9Q6zjh4zqpJgYDltdZwzQY/OKixDc54Q95LLYcJC4W6bfzSUMfRdCPdXuc+R/uUd7t+V+x0mXO9Twi4IIXMVkkzVhCmYuhz+9FP3XPSzNL5dQvls3ZutAgMBAAECgYBtWwlar0MsJixwi3C68nLbMzgMU3M8S3gf+EkuNjiBKn9hF3Gumj3ENJARIetB48lk1Q2JAcKOAEZQ/5kTkjrxj9TIjynoaSBUUyxPoeTl4FVx+Rb14ZhWYEwGiBUZenOHmAPmfNm0mgFj4GLC569CMCiP/rDUCKFiBH0y+HQoAQJBAOdkKvJKiB38GxDjWBKlJd9l31uIRJgi8xM3RKJco+SbzrJZPYXna2jhFnD17JBrYIU8uGVjipsScZ2FIrAaQF8CQQDnI/CnhSQILIJw0COQcQwQTIJiVfftVcvjS9pS72rP/FYJcYw0X96xEMArq5ZElwVQzRld6I7rUUPejanrRu9zAkBch2rBDtrVmr28CB1s/0tgxR0HPjun1rn7Iiu4/XCwdzm1iggXJs6F1xShQUZDDh0/ymM2cBK6Jir2wYl4gFbjAkA4/2rb1IAzJ86uxRTQDKB98HACkdKGiy6xCUZ4RyPe0AnjYEXDsJR4s3nACxsOJPOhnkIQH0YHD04eNm7dXuuzAkBn4E013cVv3HN2ETVKoPuGptrnPWJVvJ/jq/v3QLvJP+REp0/oQkeTdLiJQl8Ysg0DG9D33OjukRH3hD2ZhZS+";

	// @Before
	public void setUp() throws Exception {
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥： \n\r" + privateKey);
	}

	// @Test
	public void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String inputStr = "abc";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData, privateKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

	}

	@Test
	public void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String inputStr = "我来测试RSA加解密";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

		byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);

		// byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);
		//
		// byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData, privateKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "加密后: " + encodedData + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = RSACoder.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = RSACoder.verify(encodedData, publicKey, sign);
		System.err.println("状态:\r" + status);
		assertTrue(status);

	}

	@Test
	public void testEncrypt0() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String inputStr = "sign";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

		byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

	}

	@Test
	public void testSign0() throws Exception {
		System.err.println("私钥签名——公钥验证签名");
		String inputStr = "sign";
		byte[] data = inputStr.getBytes();

		// 产生签名
		String sign = RSACoder.sign(data, privateKey);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = RSACoder.verify(data, publicKey, sign);
		System.err.println("状态:\r" + status);
		assertTrue(status);

	}
}
