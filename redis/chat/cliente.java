package chat;

import java.util.Scanner;
import redis.clients.jedis.Jedis;

public class cliente {
    private static String miNick=null;
    private static Scanner leer = new Scanner(System.in);
    public static Jedis jedis = new Jedis("redis-14505.c241.us-east-1-4.ec2.cloud.redislabs.com", 14505);
    public static void main (String [] www){
        //Para la autendicación en redis
        jedis.auth("dSSj6jmUk1FOanYEtwKZPy8CsSfOtZcG");
        //Inicia programa
        solicitaNick();
    }
    public static void solicitaNick(){
        //Solicitar un nickname
        System.out.print("Ingrese su nickname: ");
        String nuevoNickname = leer.nextLine();
        Long resultado = jedis.sadd("nicks", nuevoNickname);
        if(resultado == 0){
            System.out.println("El nickname <<"+nuevoNickname+">> ya está siendo utilizado, intente con otro nickname...");
        } else {
            miNick = nuevoNickname;
            System.out.println("Hola, "+miNick);
        }
        //Si el nickname ya está utilizado, solicitar nuevamente.

    }
}
