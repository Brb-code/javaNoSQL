import redis.clients.jedis.Jedis;
import java.util.Scanner;

public class Practica05BaseDatos300920 {
    public static void main(String [] www){
        Jedis jedis = new Jedis( "redis-14505.c241.us-east-1-4.ec2.cloud.redislabs.com", 14505 );
        jedis.auth("dSSj6jmUk1FOanYEtwKZPy8CsSfOtZcG");
        //Jedis jedis = new Jedis("127.0.0.1:port");
        System.out.println("Ping: " + jedis.ping());
        System.out.println(jedis.get("Carlos"));
        jedis.rpush("tickets", "CJ - Carlos");
        System.out.println(jedis.lrange("tickets", 0, -1));
        String atendido = jedis.lpop("tickets");
        System.out.println(atendido);
        System.out.println("Bienvenid@ al banco de la fortuna" +
                "\nSeleccione una opcion"+
                "\n1) CJ"+
                "\n2) PL"+
                "\n3) CR"+
                "\n4) AM");
        Scanner leer= new Scanner(System.in);
        int opc=leer.nextInt();
         switch (opc){
             case 1:
                jedis.rpush("tickets", "CJ - Carlos");
                break;
            case 2:
                jedis.rpush("tickets", "PL - Carlos");
                break;
            case 3:
                jedis.rpush("tickets", "CR - Carlos");
                break;
            case 4:
                jedis.rpush("tickets", "AM - Carlos");
                break;
            default:
                System.out.println("Opcion incorrecta...:(");
        }
         System.out.println(jedis.lrange("tickets", 0, -1));
    }
}

// https://github.com/Brb-code/javaNoSQL/blob/master/redis/principal.java

// javac -cp ".:/home/usuario/Documents/TrabajosUbi/Work-Me/06Sem/BaseDatos03/Practica05BaseDatos300920/jedis-2.9.0.jar" Practica05BaseDatos300920.java
// java -cp ".:/home/usuario/Documents/TrabajosUbi/Work-Me/06Sem/BaseDatos03/Practica05BaseDatos300920/jedis-2.9.0.jar" Practica05BaseDatos300920
