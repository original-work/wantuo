package com.unionx.wantuo.redis;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
@Repository
public class RedisDao { 
	  //@Autowired  
	  private ShardedJedisPool shardedJedisPool;   
	  public void set(String key, String value)  {   
	   ShardedJedis jedis =  shardedJedisPool.getResource();    
	   jedis.set(key, value);  
	  } 
	  public String get(String key)  {    
	    ShardedJedis jedis =  shardedJedisPool.getResource();    
	    return jedis.get(key);  
	  }
}