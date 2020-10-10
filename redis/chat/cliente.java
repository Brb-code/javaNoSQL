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
        do {
            solicitaNick();
            //Si el nickname ya está utilizado, solicitar nuevamente.
        } while (miNick == null);
        //Indicar al usuario las conexiones en sala.
        System.out.println("Hay "+cntConectados()+" personas en sala...");
        do {
            menu();

        } while(opciones()!=0);
        salir();
    }
    public static void solicitaNick(){
        //Solicitar un nickname
        System.out.print("\nIngrese su nickname: ");
        String nuevoNickname = leer.nextLine();
        Long resultado = jedis.sadd("nicks", nuevoNickname);
        if(resultado == 0){
            System.out.println("El nickname <<"+nuevoNickname+">> ya está siendo utilizado, intente con otro nickname...");
        } else {
            miNick = nuevoNickname;
            System.out.println("Hola, "+miNick);
        }

    }
    public static long cntConectados(){
        return jedis.scard("nicks");
    }
    public static void menu(){
        System.out.print("Quieres: \n" +
                "1) Ver quíen está conectad@\n" + //SMEMBERS
                "2) Enviar mensaje\n" +
                "3) Ver estados\n" +
                "4) Contacto directo con\n" +
                "5) Ver mensajes\n" +
                "0) Salir\n" +
                "Seleccione una opción: ");
    }
    public static int opciones(){
        int opc = leer.nextInt();
        return opc;
    }
    public static void salir(){
        System.out.println("Gracias, vuelve pronto....");
    }
}
