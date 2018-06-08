
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class calculadora_server {
    public static void main(String[] args) throws IOException {
        System.out.println("                              Calculadora Server");
        ServerSocket servidor = new ServerSocket(10005);
        System.out.println("Porta 12345 aberta!");
        System.out.println("Aguardando conexão do cliente...");
        while (true) {
            Socket cliente = servidor.accept();
            // Cria uma thread do servidor para tratar a conexão
            calculadora tratamento = new calculadora(cliente);
            Thread t = new Thread(tratamento);
            // Inicia a thread para o cliente conectado
            t.start();
        }
    }
}
