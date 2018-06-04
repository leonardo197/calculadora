
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class calculadora implements Runnable{
    public Socket cliente;
    public float v1=0,v2=0,t=0;
    public String operador;
    public calculadora(Socket cliente){
        this.cliente = cliente;
    }
    public void run(){
        calculos c =new calculos();
        String operador;
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());

        try (
            PrintWriter out = new PrintWriter(this.cliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));) {
            String inputLine, outputLine;
            outputLine="                              Calculadora";
            System.out.println("server: " + outputLine);
            out.println(outputLine);


            do {
                do {
                    //-------------------primeiro
                    try {
                        outputLine="                Insira um numero:";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                        inputLine = in.readLine();
                        System.out.println("Client: " + inputLine);
                        this.v1 = Float.parseFloat(inputLine);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Numero com formato errado!\n");
                    }
                }while (true);
                do {
                    //-------------------operador
                    try {
                        outputLine="                Insira um operador :";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                        inputLine = in.readLine();
                        System.out.println("Client: " + inputLine);
                        this.operador = inputLine;
                        if (this.operador.equals("som")||this.operador.equals("sub")||this.operador.equals("div")||this.operador.equals("mult"))
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Operador com formato errado!");
                    }
                }while (true);
                do {
                    //-------------------segundo
                    try {
                        outputLine="                Insira segundo numero:";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                        inputLine = in.readLine();
                        System.out.println("Client: " + inputLine);
                        this.v2 = Float.parseFloat(inputLine);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Numero com formato errado!");
                    }
                }while (true);
//-----------------------operador
                if (this.operador.equals("som")){
                     this.t=c.som(this.v1,this.v2);
                 }else if (this.operador.equals("sub")){
                     this.t=c.sub(this.v1,this.v2);
                 }else if (this.operador.equals("div")){
                     this.t=c.div(this.v1,this.v2);
                 }else if (this.operador.equals("mult")){
                     this.t=c.mult(this.v1,this.v2);
                 }
                 outputLine=""+this.t;

                System.out.println("server: " + outputLine);
                out.println(outputLine);

                inputLine = in.readLine();
                System.out.println("Client: " + inputLine);


                inputLine = in.readLine();
                System.out.println("Client: " + inputLine);


                    System.out.println("server: " + inputLine);
                outputLine = ("server: " + inputLine);
                out.println(outputLine);
                if (inputLine.equals("by")) {
                    break;
                }
            } while (true);


            System.out.println("Fim do Serviso!");




            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
