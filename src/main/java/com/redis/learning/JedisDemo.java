package com.redis.learning;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Classname JedisDemo
 * @Description TODO
 * @Date 2021/1/3 20:12
 * @Created by laohuang
 */
public class JedisDemo {

    public void demo(){
        // 设置ip和端口
        Jedis jedis = new Jedis("ip地址",6379);
        jedis.auth("密码");// 密码登录
        // 保存数据
        jedis.set("name","test");
        // 获取数据
        String value = jedis.get("name");
        System.out.println(value);
        // 释放资源
        jedis.close();
    }


    // 使用连接池的方式进行
    public void demo2(){
        // 设置连接池的配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(30);
        // 设置最后空闲连接数
        config.setMaxIdle(10);

        // 获得连接池
        JedisPool jedisPool=new JedisPool(config,"ip地址",6379);

        Jedis jedis=null;

        try{
            jedis=jedisPool.getResource(); // 获取连接
            jedis.auth("密码"); // 设置密码
            jedis.set("name", "tes2"); // 设置值
            String value=jedis.get("name"); // 获取值
            System.out.println(value);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
    }

    public static void main(String[] args){
        JedisDemo jedisDemo = new JedisDemo();
        jedisDemo.demo2();
    }

}
