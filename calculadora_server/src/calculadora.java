import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class calculadora implements Runnable{
    public Socket cliente;

    public calculadora(Socket cliente){
        this.cliente = cliente;
    }
    public void run(){
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());

        try {
            PrintWriter out = new PrintWriter(this.cliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));{
//-------------------

                do {
                    String inputLine, outputLine;
                    inputLine = in.readLine();
                    System.out.println("Client: " + inputLine);

                        System.out.println("server: " + inputLine);
                    outputLine = ("server: " + inputLine);
                    out.println(outputLine);
                    if (inputLine.equals("by")) {
                        break;
                    }
                } while (true);

//------------

            }
            Scanner s = null;
            s = new Scanner(this.cliente.getInputStream());

            //Exibe mensagem no console
            while(s.hasNextLine()){
                //cod
            }

            //Finaliza objetos
            s.close();
            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
