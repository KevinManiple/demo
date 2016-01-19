package com.kingdee.net.ftp;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.connection.balancer.RandomLoadBalancer;

import utils.JedisCacheManager;

public class JedisTest {

	public static void main(String[] args) {
		System.out.println(JedisCacheManager.getInstance().setex("hello", "world", 4000));
		System.out.println(JedisCacheManager.getInstance().get("hello"));
		Config config = new Config();
		config.useMasterSlaveServers().setMasterAddress("172.20.10.48:6379")
		.setLoadBalancer(new RandomLoadBalancer())
		.addSlaveAddress("172.20.10.48:16379","");
		RedissonClient client = Redisson.create(config);
		System.out.println(client.getKeys().getKeysByPattern("k"));
		
	}
}
