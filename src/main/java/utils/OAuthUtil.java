package utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 签名验证
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class OAuthUtil {

	public static final int		CONNECTION_TIME_OUT	= 50000;
	public static final int		READ_TIME_OUT		= 300000;
	public static final String	ENC					= "UTF-8";
	public static final String	POST				= "POST";
	public static final String	GET					= "GET";
	public static final String	AUTHORIZATION		= "authorization";
	public static final String	CONTENT_TYPE		= "Content-Type";

	/**
	 * 签名【客户端】
	 * 
	 * @param nonce
	 * @param appid
	 * @param appSecret
	 * @return
	 * @throws UnsupportedEncodingException
	 * @see
	 */
	public static String auth(String nonce, String appid, String appSecret) throws UnsupportedEncodingException {
		String authorization = "InternetFinanceAuth \"version\":\"%s\", \"appid\":\"%s\", \"timestamp\":%d, \"nonce\":\"%s\", \"sign\":\"%s\"";
		String version = "1.0";
		// appid = URLEncoder.encode(appid, ENC);
		appid = Base64.encodeBase64String(appid.getBytes(ENC));
		long timestamp = System.currentTimeMillis();
		String sign = shaHex(version, appid, timestamp + "", nonce, appSecret);
		sign = URLEncoder.encode(sign, ENC);
		sign = Base64.encodeBase64String(sign.getBytes(ENC));
		authorization = String.format(authorization, version, appid, timestamp, nonce, sign);
		return authorization;
	}

	/**
	 * 签名【服务端】
	 * 
	 * @param timestamp
	 * @param nonce
	 * @param appid
	 * @param appSecret
	 * @return
	 * @throws UnsupportedEncodingException
	 * @see
	 */
	public static String auth(long timestamp, String nonce, String appid, String appSecret) throws UnsupportedEncodingException {
		String authorization = "InternetFinanceAuth \"version\":\"%s\", \"appid\":\"%s\", \"timestamp\":%d, \"nonce\":\"%s\", \"sign\":\"%s\"";
		String version = "1.0";
		// appid = URLEncoder.encode(appid, ENC);
		appid = Base64.encodeBase64String(appid.getBytes(ENC));
		String sign = shaHex(version, appid, timestamp + "", nonce, appSecret);
		sign = URLEncoder.encode(sign, ENC);
		sign = Base64.encodeBase64String(sign.getBytes(ENC));
		authorization = String.format(authorization, version, appid, timestamp, nonce, sign);
		return authorization;
	}

	/**
	 * 获取随机数
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 * @see
	 */
	public static String getNonce() throws UnsupportedEncodingException {
		return URLEncoder.encode(UUID.randomUUID().toString(), ENC);
	}

	/**
	 * SHA1加密
	 * 
	 * @param data
	 * @return
	 * @see
	 */
	public static String shaHex(String... data) {
		Arrays.sort(data);
		String join = StringUtils.join(data);
		String sign = DigestUtils.sha256Hex(join);
		return sign;
	}

	private static LinkedList<String>	keys	= new LinkedList<String>();

	public static void main(String[] args) throws IOException, Exception {
		// 1. 从后台获取随机数
		String sessionNonce = "4a003180-f589-4647-b5f5-c5e382da3e0d";
		keys.add(sessionNonce);
		String nonce = sessionNonce;

		// 2. 发送签名
		if (!keys.contains(nonce)) {
			System.out.println("验证失败");
		}
		String result = auth(nonce, "kis", "kis");
		System.out.println(result);
		// 3. 发送成功后删除session随机数
		keys.remove(sessionNonce);

		// 4. 后台进行签名验证
		if (!result.startsWith("InternetFinanceAuth")) {
			System.out.println("验证失败");
		}
		String jsonResult = "{" + result.replaceFirst("InternetFinanceAuth", "") + "}";
		System.out.println(jsonResult);
		JSONObject object = JSON.parseObject(jsonResult);
		final String version = object.getString("version");
		if (!version.equals("1.0")) {
			System.out.println("验证失败");
		}
		String appid = object.getString("appid");
		appid = new String(Base64.decodeBase64(appid), ENC);
		long timestamp = object.getLongValue("timestamp");
		final String noncea = object.getString("nonce");
		String verify = auth(timestamp, noncea, appid, "kis");
		System.out.println(verify);
		if (!verify.equals(result)) {
			System.out.println("验证失败");
		}

		// 5. 第二次发送签名
		if (!keys.contains(nonce)) {
			System.out.println("验证失败");
		}
		result = auth(nonce, "kis", "kis");

	}

}
