import redis.clients.jedis.Jedis;

public class principal {
    public static void main(String [] www){
        Jedis jedis = new Jedis( "redis-14505.c241.us-east-1-4.ec2.cloud.redislabs.com", 14505);
        jedis.auth("dSSj6jmUk1FOanYEtwKZPy8CsSfOtZcG");
        System.out.println("Ping: " + jedis.ping());
        System.out.println(jedis.get("Israel"));
    }
}
