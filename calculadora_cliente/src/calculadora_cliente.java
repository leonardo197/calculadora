import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class calculadora_cliente {
    public static void main(String[] args) throws IOException {
        String hostName = "127.0.0.1";//args[0];//127.0.0.1
        int portNumber = 10005;
        Scanner teclado = new Scanner(System.in);
        System.out.println("                              Calculadora");
        System.out.print("Server ip: ");
        //hostName=teclado.next().toString();
        System.out.println();
        System.out.print("Server porta: ");
        //portNumber=teclado.nextInt();
        System.out.println();

        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;


            do {
                fromUser = stdIn.readLine();
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
                fromServer=in.readLine();
                System.out.println("Server: " + fromServer);

                if(fromUser.equals("by")){break;}
            }while (true);


        } catch (UnknownHostException e) {
            System.err.println("Não sabe sobre o host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Não foi possível obter a forma de I/O da conexão para " + hostName);
            System.exit(1);
        }


    }
}
