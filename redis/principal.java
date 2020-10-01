import redis.clients.jedis.Jedis;

import java.util.Scanner;

public class principal {
    public static void main(String [] www){
        //Para la nube
        Jedis jedis = new Jedis( "redis-14505.c241.us-east-1-4.ec2.cloud.redislabs.com", 14505);
        jedis.auth("dSSj6jmUk1FOanYEtwKZPy8CsSfOtZcG");
        //Para localhost
        //Jedis jedis = new Jedis( "127.0.0.1", 6379);

        System.out.println("Ping: " + jedis.ping());
        System.out.println(jedis.get("Israel"));

        //jedis.rpush("tickets", "CAJA - Israel");

        System.out.println(jedis.lrange("tickets", 0, -1));

        //String atendido = jedis.lpop("tickets");
        //System.out.println(atendido);

        System.out.println("Bienvenid@ al banco de la fortuna" +
                "\nSeleccione una opción:" +
                "\n1) Cajas - CJ" +
                "\n2) Plataforma - PL" +
                "\n3) Créditos - CR" +
                "\n4) Adulto mayor - AM");
        Scanner leer = new Scanner(System.in);
        int opc = leer.nextInt();
        switch (opc){
            case 1:
                jedis.rpush("tickets", "CJ - Israel");
                break;
            case 2:
                jedis.rpush("tickets", "PL - Israel");
                break;
            case 3:
                jedis.rpush("tickets", "CR - Israel");
                break;
            case 4:
                jedis.rpush("tickets", "AM - Israel");
                break;
            default:
                System.out.println("Opción incorrecta... :(");
        }
        System.out.println(jedis.lrange("tickets", 0, -1));
    }
}
