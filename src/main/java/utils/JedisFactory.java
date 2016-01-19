package utils;

import org.apache.commons.lang3.StringUtils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisFactory {

	private static JedisFactory	instance		= new JedisFactory();
	private static JedisPool	jedisPool;
	public static final String	HOST			= "172.20.10.48";
	public static final int		PORT			= 6379;
	public static final int		TIMEOUT			= 50000;
	public static final int		MAX_IDLE		= 128;
	public static final int		MIN_IDLE		= 0;
	public static final int		MAX_WAIT_MILLIS	= 15 * 1000;
	public static final String	PASSWORD		= "";

	private JedisFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(MAX_IDLE);
		poolConfig.setMinIdle(MIN_IDLE);
		poolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
		if (StringUtils.isBlank(PASSWORD)) {
			jedisPool = new JedisPool(poolConfig, HOST, PORT, TIMEOUT);
		} else {
			jedisPool = new JedisPool(poolConfig, HOST, PORT, TIMEOUT, PASSWORD);
		}
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public static JedisFactory getInstance() {
		return instance;
	}

}
