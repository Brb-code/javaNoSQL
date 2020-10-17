/*
        _                    _____          _ _
       | |                  |  __ \        | (_)
       | | __ ___   ____ _  | |__) |___  __| |_ ___
   _   | |/ _` \ \ / / _` | |  _  // _ \/ _` | / __|
  | |__| | (_| |\ V / (_| | | | \ \  __/ (_| | \__ \
   \____/ \__,_| \_/ \__,_| |_|  \_\___|\__,_|_|___/

   Estudiante: Carlos Cusicanqui
   Materia: Base de Datos 03
   Universidad Boliviana de Informática

*/

//package chat;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Practica09BaseDatos141020 {
///////////////////////////////////////////////////////////////////////////////////////////////////
    private static String miNick=null;
    private static Scanner leer = new Scanner(System.in);
    public static Jedis jedis = new Jedis("redis-14505.c241.us-east-1-4.ec2.cloud.redislabs.com", 14505);
    public static void main (String [] www){
	//Para la autendicación en redis
	jedis.auth("dSSj6jmUk1FOanYEtwKZPy8CsSfOtZcG");
	//Vaciar nicks o eliminar un documento
	//jedis.del("nicks");
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
	switch (opc){
	case 1:
	    personasenlinea();
	    break;
	}
	return opc;
    }
    public static void personasenlinea(){
	Set<String> nicksenL = jedis.smembers("nicks");
	Object [] k = nicksenL.toArray();
	System.out.print("Se encuentran conetad@s: ");
	for (int i=0; i<k.length; i++)
	    System.out.print("@"+k[i]+", ");
    }
    public static void salir(){
	jedis.srem("nicks", miNick);
	System.out.println("Gracias, vuelve pronto....");
	miNick = null;
    }
}




///////////////////////////////////////////////////////////////////////////////////////////////////

//En Emacs
//M-x compile
// javac -cp ".:/home/usuario/Documents/TrabajosUbi/Work-Me/06Sem/BaseDatos03/jedis-2.9.0.jar"  Practica09BaseDatos101420.java
// java -cp ".:/home/usuario/Documents/TrabajosUbi/Work-Me/06Sem/BaseDatos03/jedis-2.9.0.jar"  Practica09BaseDatos101420


// En Vim
// time javac -cp ".:/home/usuario/Documents/TrabajosUbi/Work-Me/06Sem/BaseDatos03/jedis-2.9.0.jar" Practica09BaseDatos101420.java
// time java -cp ".:/home/usuario/Documents/TrabajosUbi/Work-Me/06Sem/BaseDatos03/                     Practica05BaseDatos300920/jedis-2.9.0.jar" Practica06BaseDatos101020
