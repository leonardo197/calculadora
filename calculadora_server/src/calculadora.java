
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;

public class calculadora implements Runnable {
    public Socket cliente;
    DecimalFormat formatter = new DecimalFormat("0.000000");
    public float v1 = 0, v2 = 0, t = 0;
    public String operador, memoria = "n";

    public calculadora(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        calculos c = new calculos();
        System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());

        try (PrintWriter out = new PrintWriter(this.cliente.getOutputStream(), true);
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
                            outputLine = "                Insira numero:";
                            System.out.println("server: " + outputLine);
                            out.println(outputLine);
                            inputLine = in.readLine();
                            System.out.println("Client: " + cliente.getRemoteSocketAddress() + " Diz " + inputLine);
                            this.v1 = Float.parseFloat(inputLine);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Numero com formato errado!");
                        }
                    } else break;

                } while (true);
                do {
                    //-------------------operador
                    try {
                        outputLine = "                Insira operador :";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                        inputLine = in.readLine();
                        System.out.println("Client: " + cliente.getRemoteSocketAddress() + " Diz " + inputLine);
                        this.operador = inputLine;
                        if (this.operador.equals("som") || this.operador.equals("sub") || this.operador.equals("div") || this.operador.equals("mul") ||
                                this.operador.equals("pow") || this.operador.equals("fat") || this.operador.equals("sin") || this.operador.equals("cos") ||
                                this.operador.equals("cos"))
                            break;
                    } catch (NumberFormatException e) {
                        System.out.println("Operador com formato errado!");
                    }
                } while (true);
                if (this.operador.equals("som") || this.operador.equals("sub") || this.operador.equals("div") || this.operador.equals("mul")) {
                    do {
                        //-------------------segundo
                        try {
                            outputLine = "                Insira numero:";
                            System.out.println("server: " + outputLine);
                            out.println(outputLine);
                            inputLine = in.readLine();
                            System.out.println("Client: " + cliente.getRemoteSocketAddress() + " Diz " + inputLine);
                            this.v2 = Float.parseFloat(inputLine);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Numero com formato errado!");
                        }
                    } while (true);
                }
//-----------------------operador
                try {
                    switch (this.operador) {
                        case "som":
                            this.t = c.som(this.v1, this.v2);
                            break;
                        case "sub":
                            this.t = c.sub(this.v1, this.v2);
                            break;
                        case "div":
                            this.t = c.div(this.v1, this.v2);
                            break;
                        case "mul":
                            this.t = c.mult(this.v1, this.v2);
                            break;
                        case "pow":
                            this.t = (float) c.pow(this.v1, this.v2);
                            break;
                        case "fat":
                            this.t = c.fatorial(v1);
                            break;
                        case "sin":
                            this.t = (float) c.sin(v1);
                            break;
                        case "cos":
                            this.t = (float) c.cos(v1);
                            break;
                        case "tan":
                            this.t = (float) c.tan(v1);
                            break;
                    }
                    outputLine = "" + formatter.format(this.t);
                    System.out.println("server: " + outputLine);
                    out.println(outputLine);
                } catch (NumberFormatException e) {
                    System.out.println("Erro na operação!!!");
                }
                //--------------------------pergunta se quer Continuar
                do {
                    outputLine = "                Continuar a calcular??(s)(n)";
                    System.out.println("server: " + outputLine);
                    out.println(outputLine);
                    inputLine = in.readLine();
                    System.out.println("Client: " + cliente.getRemoteSocketAddress() + " Diz " + inputLine);
                    if (inputLine.equals("n") || inputLine.equals("N")) {
                        outputLine = "by";
                        System.out.println("server: " + outputLine);
                        out.println(outputLine);
                    }
                    if (inputLine.equals("n") || inputLine.equals("N") || inputLine.equals("s") || inputLine.equals("S")) {
                        break;
                    }
                } while (true);
                if (inputLine.equals("n") || inputLine.equals("N")) {
                    break;
                }
                //--------------------------pergunta se quer Continuar com memoria
                do {
                    outputLine = "                guardar valor na memória?(s)(n)";
                    System.out.println("server: " + outputLine);
                    out.println(outputLine);
                    inputLine = in.readLine();
                    System.out.println("Client: " + cliente.getRemoteSocketAddress() + " Diz " + inputLine);
                    if (inputLine.equals("s") || inputLine.equals("S")) {
                        this.v2 = this.t;
                        this.v1 = this.t;
                        this.memoria = "s";
                        break;
                    } else {
                        this.v2 = 0;
                        this.v1 = 0;
                        this.memoria = "n";
                        break;
                    }
                } while (true);
            } while (true);
            this.cliente.close();
        } catch (IOException e) {
        }
        System.out.println("Fim do Serviso!");
    }
}
