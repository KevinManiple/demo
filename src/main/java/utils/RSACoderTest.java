package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class RSACoderTest {

	private String	publicKey	= "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAISDgyUft9yA+caFXlPgZAVPVvj1hjIlbj+onLlfDj/8VvX5GeD/4R1m6mVa+XY4+hjVYof8FxstfBqYvP9C/McCAwEAAQ==";
	private String	privateKey	= "MIIBOQIBAAJBAISDgyUft9yA+caFXlPgZAVPVvj1hjIlbj+onLlfDj/8VvX5GeD/4R1m6mVa+XY4+hjVYof8FxstfBqYvP9C/McCAwEAAQJAf4PyOlIoYqc2NGP4nmwqs7VicxYJOc4tJlMB5ZvkIYsyR9x/VapIWG7idnYY81VIatKGNX6BpQ8cZXDMGf8A2QIhANX3Wb4dm6JYhCzwKnm69abUTy58yQA2MwLIJAvC1yejAiEAnovOVPir528vS37XF1MPSTdmlt0FpzAvpiFpGMT3uI0CIDuHAm+zGw6So8tA0gBl9FwCqzjavK0TZyO5/NVM0sETAiBlwXkF1CwQd621GI1X7PkslqADR4uvSB/s0hivywrqqQIgVlS5UoAu4T9XNwBkOcU2sk0YBa2cxukXISeDNdOpcC4=";

	//@Before
	public void setUp() throws Exception {
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		System.err.println("公钥: \n\r" + publicKey);
		System.err.println("私钥： \n\r" + privateKey);
	}

	// @Test
	public void tests() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String inputStr = "abc";
		byte[] data = inputStr.getBytes("UTF-8");

		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);
		String decodeS = new String(encodedData, "UTF-8");
		System.err.println("加密后：" + decodeS);
		System.err.println("加密后：" + encodedData);

		byte[] decodedData = RSACoder
				.decryptByPrivateKey(
						"MTUm9ldmK/OA4jfS37wGi2KWgDcr+YGPkrm/RpxAv1O9d6zVtDY6aYPCeedU1q1Xg9D4HyPUFGMIrZJHtW/4gkNxmXr+RsBMB3rNLHwxTZH4T+i4IGXDy9wTFRiWzPl54/7OIeukphYfhrKK364p/q5IsVLw7iNaYxHNkybm1+A="
								.getBytes("UTF-8"), privateKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

	}

	@Test
	public void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String inputStr = "abc123";
		byte[] data = inputStr.getBytes("UTF-8");
		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);
//		String temp = "ZmI0YmJlZGZkZDg5YmQyMzEwYjFkOTE2ZWE0MzBmOWI2NWQ3MzBiYWQ4M2Q4YWYyYTNlOWRhYTI4NTU3OGJiODZlNzExOTk1ZDgxMjg5NTcxY2YyZGY3YTMzNTk2MTI0ZTAxMTQ1NDAxZmMxMjgzMjc5OTVmZDEzNzA2YWZhYTE2MDQyYzA1Zjc0MTI5MDU3NTcxYzAwODQ4ODBmNmQzOTM2ZDliNzFjOTNjMQ==";
//		byte[] decodedData = RSACoder.decryptByPrivateKey(Coder.decryptBASE64(temp), privateKey);
		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData, privateKey);
		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n加密后：" + Coder.encryptBASE64(encodedData) + "\n解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

	}

	// @Test
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
