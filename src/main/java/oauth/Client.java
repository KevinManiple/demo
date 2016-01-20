package oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class Client {

	public static final String	ENC	= "UTF-8";

	/**
	 * SHA加密
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

	/**
	 * 签名
	 * 
	 * @param nonce 从接口获取
	 * @param appid 金融提供
	 * @param appSecret 金融提供
	 * @return
	 * @throws UnsupportedEncodingException
	 * @see
	 */
	public static String auth(String nonce, String appid, String appSecret) throws UnsupportedEncodingException {
		String authorization = "InternetFinanceAuth \"version\":\"%s\", \"appid\":\"%s\", \"timestamp\":%d, \"nonce\":\"%s\", \"sign\":\"%s\"";
		String version = "1.0";
		appid = Base64.encodeBase64String(appid.getBytes(ENC));
		long timestamp = System.currentTimeMillis();
		String sign = shaHex(version, appid, timestamp + "", nonce, appSecret);
		sign = URLEncoder.encode(sign, ENC);
		sign = Base64.encodeBase64String(sign.getBytes(ENC));
		authorization = String.format(authorization, version, appid, timestamp, nonce, sign);
		return authorization;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(auth(getNonce(), "test", "test"));
	}

	public static String getNonce() {
		BufferedReader br = null;
		final StringBuilder result = new StringBuilder();
		try {
			final String requestURL = "http://172.20.10.105/kisPayment/nonce";
			URL url = new URL(requestURL);
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(50000);
			conn.setReadTimeout(50000);
			conn.connect();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENC));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			return JSON.parseObject(JSON.parseObject(result.toString()).getString("data")).getString("nonce");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException e) {
			}
		}
	}

}
