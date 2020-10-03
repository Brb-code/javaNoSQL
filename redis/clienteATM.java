import java.util.Scanner;
import redis.clients.jedis.Jedis;

public class clienteATM {
    public static void main(String [] www){
        //Librerias
        Jedis jedis = new Jedis("redis-14505.c241.us-east-1-4.ec2.cloud.redislabs.com", 14505);
        jedis.auth("dSSj6jmUk1FOanYEtwKZPy8CsSfOtZcG");

        while(){

        }

        System.out.println("Bienvenid@ al banco de la fortuna" +
                "\nSeleccione una opción:" +
                "\n1) Cajas - CJ" +
                "\n2) Plataforma - PL" +
                "\n3) Créditos - CR" +
                "\n4) Adulto mayor - AM" +
                "\n0) Salir");

        // Para borrar la clave
        // jedis.del("tickets");

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
                jedis.lpush("tickets", "AM - Israel");
                break;
            case 0:
                System.out.println("Gracias...");
                break;
            default:
                System.out.println("Opción incorrecta... :(");
        }

        System.out.println(jedis.lrange("tickets", 0, -1));
    }
}
