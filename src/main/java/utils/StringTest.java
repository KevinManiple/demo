package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class StringTest {

	private static final String	ENC	= "UTF-8";

	public static void main(String[] args) throws Exception {
		System.out.println(appAuth2Treaty("test", "test"));
	}

	public static String appAuth2Treaty(String appid, String appSecret) throws UnsupportedEncodingException {
		String authorization = "OpenAuth2 version=\"%s\", appid=\"%s\", timestamp=%d, nonce=\"%s\", sign=\"%s\"";
		String version = "1.1";
		appid = URLEncoder.encode(appid, ENC);
		long timestamp = System.currentTimeMillis();
		String nonce = URLEncoder.encode(UUID.randomUUID().toString(), ENC);
		String sign = shaHex(version, appid, timestamp + "", nonce, appSecret);
		sign = URLEncoder.encode(sign, ENC);
		authorization = String.format(authorization, version, appid, timestamp, nonce, sign);
		return authorization;
	}

	@SuppressWarnings("deprecation")
	public static String shaHex(String... data) {
		Arrays.sort(data);
		String join = StringUtils.join(data);
		String sign = DigestUtils.shaHex(join);
		return sign;
	}

}
