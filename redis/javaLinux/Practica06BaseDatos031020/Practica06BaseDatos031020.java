import java.util.Scanner;
import redis.clients.jedis.Jedis;

public class Practica06BaseDatos031020 {
    //Correlativos
    public static int correlativoCJ = 1;
    public static int correlativoPL = 1;
    public static int correlativoCR = 1;
    public static int correlativoAM = 1;
    public static void main(String [] www){

        int externo = 1;
        for (int i=0; i<externo; i++){
            imprimirMenu();
            if(opciones() != 0)
                externo ++;
        }

        /*
        do {
            imprimirMenu();
        }while (opciones() != 0);
        */
        // Para borrar la clave
        // jedis.del("tickets");


    }
    public static void imprimirMenu(){
        System.out.println("Bienvenid@ al banco de la fortuna" +
                "\nSeleccione una opción:" +
                "\n1) Cajas - CJ" +
                "\n2) Plataforma - PL" +
                "\n3) Créditos - CR" +
                "\n4) Adulto mayor - AM" +
                "\n0) Salir");
    }
    public static int opciones(){
        //Librerias
        Jedis jedis = new Jedis("redis-14505.c241.us-east-1-4.ec2.cloud.redislabs.com", 14505);
        jedis.auth("dSSj6jmUk1FOanYEtwKZPy8CsSfOtZcG");
        Scanner leer = new Scanner(System.in);

        int opc = leer.nextInt();
        switch (opc){
            case 1:
                jedis.rpush("tickets", "CJ" + (correlativoCJ) +" - Israel");
                correlativoCJ= correlativoCJ+1;
                break;
            case 2:
                jedis.rpush("tickets", "PL" + (correlativoPL) +" - Israel");
                correlativoPL=correlativoPL+1;
                break;
            case 3:
                jedis.rpush("tickets", "CR" + (correlativoCR) +" - Israel");
                correlativoCR=correlativoCR+1;
                break;
            case 4:
                jedis.lpush("tickets", "AM" + (correlativoAM) +" - Israel");
                correlativoAM=correlativoAM+1;
                break;
            case 0:
                System.out.println("Gracias...");
                break;
            default:
                System.out.println("Opción incorrecta... :(");
        }
        System.out.println(jedis.lrange("tickets", 0, -1));
        return opc;
    }
}


// Actividad: podamos compartirel correlativo con todos los companeros para que no haya corelativo duplicado

// https://github.com/Brb-code/javaNoSQL/blob/master/redis/principal.java

// time javac -cp ".:/home/usuario/Documents/TrabajosUbi/Work-Me/06Sem/BaseDatos03/Practica05BaseDatos300920/jedis-2.9.0.jar" Practica06BaseDatos031020.java
// time java -cp ".:/home/usuario/Documents/TrabajosUbi/Work-Me/06Sem/BaseDatos03/Practica05BaseDatos300920/jedis-2.9.0.jar" Practica06BaseDatos031020
