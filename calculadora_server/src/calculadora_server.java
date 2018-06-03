import com.sun.corba.se.spi.activation.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class calculadora_server {

    public static void main(String[] args) throws IOException {
        String hostName = "192.168.1.5";
        int portNumber = 10002;
        Scanner teclado = new Scanner(System.in);
        System.out.println("                              Calculadora Server");
        System.out.print("Server ip: ");
        //hostName=teclado.next().toString();
        System.out.println();



        ServerSocket servidor = new ServerSocket (portNumber);

        System.out.println("Aguardando conexão do cliente...");

        Socket cliente = servidor.accept();
        // Cria uma thread do servidor para tratar a conexão
        calculadora s =new calculadora(cliente);
        Thread t = new Thread(s);
        // Inicia a thread para o cliente conectado
        t.start();
        }

}
