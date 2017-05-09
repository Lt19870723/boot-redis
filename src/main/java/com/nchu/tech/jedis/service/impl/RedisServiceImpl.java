package com.nchu.tech.jedis.service.impl;

import com.nchu.tech.jedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by fujianjian on 2017/5/5.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final String DEFAULT_HASH_KEY = "shenma:region";
    private static final String DEFAULT_HBXJ_REGION = "shenma:fund:hbxj:region";

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public int del(String key) {
        RedisConnection redisConnection = this.redisConnectionFactory.getConnection();
        return 0;
    }

    @Override
    public void set(String key, String value) {
        RedisConnection redisConnection = this.redisConnectionFactory.getConnection();
        redisConnection.set(key.getBytes(), value.getBytes());
    }

    @Override
    public int setnx(String key, String value) {
        return 0;
    }

    @Override
    public void hset(String key, String field, String value) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        String hkey = StringUtils.isEmpty(key) ? DEFAULT_HASH_KEY : key;
        connection.hSet(hkey.getBytes(), field.getBytes(), value.getBytes());
    }

    @Override
    public void hmset(String key, Map<byte[], byte[]> pairs) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        String hkey = StringUtils.isEmpty(key) ? DEFAULT_HBXJ_REGION : key;
        connection.hMSet(hkey.getBytes(), pairs);
    }


    /****
     * 获取redis中湖北消金的地区编码
     * @return
     */
    @Override
    public Map getHBXJRegionOnRedis() {
       return this.hgetall(DEFAULT_HBXJ_REGION);
    }

    /***
     * 获取散列中的所有的值
     * @param key
     * @return
     */
    private Map hgetall(String key) {
        RedisConnection connection = this.redisConnectionFactory.getConnection();
        Map map = connection.hGetAll(key.getBytes());
        return map;
    }

    public final static String DEFAULT_REDIS_LIST = "shenma:redis:list";

    public void lpush(String key, String value) {
        RedisConnection connection = this.redisConnectionFactory.getConnection();
        Long ret = connection.lPush(DEFAULT_REDIS_LIST.getBytes(), "test".getBytes(), "redis".getBytes(), "list".getBytes());
        System.out.println(ret);
    }

    public void rpop() throws Exception {
        RedisConnection connection = redisConnectionFactory.getConnection();
        byte[] ret = connection.rPop(DEFAULT_REDIS_LIST.getBytes());
        System.out.println(new String(ret, "UTF-8"));
    }

    public final static String DEFAULT_REDIS_SET = "shenma:redis:set";

    public void sadd() {
        RedisConnection connection = redisConnectionFactory.getConnection();
        Long ret = connection.sAdd(DEFAULT_REDIS_SET.getBytes(), "fujianjian".getBytes(), "12".getBytes(), "boy".getBytes());
        System.out.println(ret);

    }

    public void smember() {
        RedisConnection connection = redisConnectionFactory.getConnection();
        Set<byte[]> ret = connection.sMembers(DEFAULT_REDIS_SET.getBytes());
        ret.stream().map(String::new).forEach(System.out::println);
    }

    public static final String DEFAULT_REDIS_ZSET = "shenma:redis:zset";

    public void zAdd() {
        RedisConnection connection = redisConnectionFactory.getConnection();
        Boolean flag = connection.zAdd(DEFAULT_REDIS_ZSET.getBytes(), 1d, "nchufujian".getBytes());
        System.out.println(flag);
    }

    public void zAddTrup() {
        RedisConnection connection = redisConnectionFactory.getConnection();
        Set<RedisZSetCommands.Tuple> zset = new HashSet<RedisZSetCommands.Tuple>(){{
            add(new shenmaTuple("nchufu", 1));
            add(new shenmaTuple("nchufujian", 10));
            add(new shenmaTuple("nchufujianjian", 33));
            add(new shenmaTuple("nchujian", 5));
            add(new shenmaTuple("nchujianjian", 25));
            add(new shenmaTuple("nchu", 3));
        }};
        Set<shenmaTuple> smzset = new HashSet<shenmaTuple>(){{
            add(new shenmaTuple("nchufu", 1));
            add(new shenmaTuple("nchufujian", 10));
            add(new shenmaTuple("nchufujianjian", 33));
            add(new shenmaTuple("nchujian", 5));
            add(new shenmaTuple("nchujianjian", 25));
            add(new shenmaTuple("nchu", 3));
        }};

        Long ret = connection.zAdd(DEFAULT_REDIS_ZSET.getBytes(), zset);
        System.out.println(ret);
    }

    public void zRange() {
        RedisConnection connection = redisConnectionFactory.getConnection();
        Set<byte[]> ret = connection.zRange(DEFAULT_REDIS_ZSET.getBytes(), 1, 100);
        ret.stream().map(String::new).forEach(System.out::println);
        System.out.println("========================我是分割线=========================");
        Set<byte[]> revRet = connection.zRevRange(DEFAULT_REDIS_ZSET.getBytes(), 0, 100);
        revRet.stream().map(String::new).forEach(System.out::println);
        System.out.println("========================我是分割线=========================");
        Long allInfoRank = connection.zRank(DEFAULT_REDIS_ZSET.getBytes(), "nchufujianjian".getBytes());
        System.out.println(allInfoRank);
    }

    public class shenmaTuple implements RedisZSetCommands.Tuple {

        private String name;

        private int rank;

        public shenmaTuple(String name, int rank) {
            this.name = name;
            this.rank = rank;
        }
        @Override
        public Double getScore() {
            return new Double(rank);
        }

        @Override
        public byte[] getValue() {
            return name.getBytes();
        }

        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         * <p>
         * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
         * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
         * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
         * <tt>y.compareTo(x)</tt> throws an exception.)
         * <p>
         * <p>The implementor must also ensure that the relation is transitive:
         * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
         * <tt>x.compareTo(z)&gt;0</tt>.
         * <p>
         * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
         * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
         * all <tt>z</tt>.
         * <p>
         * <p>It is strongly recommended, but <i>not</i> strictly required that
         * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
         * class that implements the <tt>Comparable</tt> interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         * <p>
         * <p>In the foregoing description, the notation
         * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
         * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
         * <tt>0</tt>, or <tt>1</tt> according to whether the value of
         * <i>expression</i> is negative, zero or positive.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(Double o) {
            return getScore().compareTo(o);
        }
    }

}
