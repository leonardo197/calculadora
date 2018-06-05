
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class calculadora implements Runnable {
    public Socket cliente;
    public float v1 = 0, v2 = 0, t = 0;
    public String operador,memoria="n";

    public calculadora(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        calculos c = new calculos();
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());

        try (
                PrintWriter out = new PrintWriter(this.cliente.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));) {
            String inputLine, outputLine;
            outputLine = "                              Calculadora";
            System.out.println("server: " + outputLine);
            out.println(outputLine);


            do {
                do {
                    //-------------------primeiro
                    if (memoria.equals("n")) {
                    try {
                            outputLine = "                Insira um numero:";
                            System.out.println("server: " + outputLine);
                            out.println(outputLine);
                            inputLine = in.readLine();
                            System.out.println("Client: " + inputLine);
                            this.v1 = Float.parseFloat(inputLine);
                            break;
                        } catch(NumberFormatException e){
                            System.out.println("Numero com formato errado!");
                        }
                    }
                    else break;

                } while (true);
                do {
                    //-------------------operador
                    try {
                        outputLine = "                Insira um operador :";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                        inputLine = in.readLine();
                        System.out.println("Client: " + inputLine);
                        this.operador = inputLine;
                        if (this.operador.equals("som") || this.operador.equals("sub") || this.operador.equals("div") || this.operador.equals("mult"))
                            break;
                    } catch (NumberFormatException e) {
                        System.out.println("Operador com formato errado!");
                    }
                } while (true);
                do {
                    //-------------------segundo
                    try {
                        outputLine = "                Insira segundo numero:";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                        inputLine = in.readLine();
                        System.out.println("Client: " + inputLine);
                        this.v2 = Float.parseFloat(inputLine);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Numero com formato errado!");
                    }
                } while (true);
//-----------------------operador
                try {
                    if (this.operador.equals("som")) {
                        this.t = c.som(this.v1, this.v2);
                    } else if (this.operador.equals("sub")) {
                        this.t = c.sub(this.v1, this.v2);
                    } else if (this.operador.equals("div")) {
                        this.t = c.div(this.v1, this.v2);
                    } else if (this.operador.equals("mult")) {
                        this.t = c.mult(this.v1, this.v2);
                    } else if (this.operador.equals("mult")) {
                        this.t = (float) c.pow(this.v1, this.v2);
                    }
                    outputLine = "" + this.t;
                    System.out.println("server: " + outputLine);
                    out.println(outputLine);
                } catch (NumberFormatException e) {
                System.out.println("Erro na operação!!!");
            }
            //--------------------------pergunta se quer Continuar
                outputLine = "                Continuar a calcular(s)(n)";
                System.out.println("server: " + outputLine);
                out.println(outputLine);
                inputLine = in.readLine();
                System.out.println("Client: " + inputLine);
                if (inputLine.equals("n")||inputLine.equals("N")) {
                    outputLine = "by";
                    System.out.println("server: " + outputLine);
                    out.println(outputLine);
                    break;
                }
                //--------------------------pergunta se quer Continuar com memoria
                outputLine = "                Continuar a calcular com memoria(s)(n)";
                System.out.println("server: " + outputLine);
                out.println(outputLine);
                inputLine = in.readLine();
                System.out.println("Client: " + inputLine);
                if (inputLine.equals("s")||inputLine.equals("S")) {
                    this.v1=this.t;
                    this.memoria="s";
                }

            } while (true);


            System.out.println("Fim do Serviso!");


            this.cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
