import java.util.Scanner;
import redis.clients.jedis.Jedis;

public class clienteATM {
    //Correlativos
    //public static int correlativoCJ = 1;
    public static int correlativoPL = 1;
    public static int correlativoCR = 1;
    public static int correlativoAM = 1;
    public static Jedis jedis = new Jedis("redis-14505.c241.us-east-1-4.ec2.cloud.redislabs.com", 14505);
    public static void main(String [] www){
        jedis.auth("dSSj6jmUk1FOanYEtwKZPy8CsSfOtZcG");
        //jedis.del("tickets");
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

        Scanner leer = new Scanner(System.in);

        int opc = leer.nextInt();
        switch (opc){
            case 1:
                /* No Optimizado
                String correlativoCJ = jedis.get("correlativoCJ");
                int corrCJ = Integer.parseInt(correlativoCJ);
                jedis.rpush("tickets", "CJ" + (corrCJ) +" - Israel");
                //correlativoCJ= correlativoCJ+1;
                jedis.set("correlativoCJ", (corrCJ + 1)+"");
                break;
                /* fin no optimizar */
                /* Optimizado */
                String correlativoCJ = jedis.get("correlativoCJ");
                if(correlativoCJ == null) {
                    jedis.set("correlativoCJ", "1");
                    correlativoCJ = "1";
                }
                jedis.rpush("tickets", "CJ" + (correlativoCJ) +" - Israel");
                jedis.incr("correlativoCJ");
                break;
                /* fin no optimizar */
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
