import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;


public class Consumidor {
    // Lista com todas as instâncias de Consumir (cada uma rodando em uma Thread)
    private ArrayList<Consumir> consumidores = new ArrayList<Consumir>();

    // Socket servidor, socket e Scanner para ler do socket
    private ServerSocket listener;
    private Socket socket;
    private Scanner in;

    // Construtor, jah implementado... nao alterar!
    Consumidor(int porta) throws IOException {
// IMPLEMENTAR !!!!
        // Abrir uma poRta de escuta (utilizando o ServerSocket)  e armazenar em listener.
    	ServerSocket listener = new ServerSocket (porta);	
// IMPLEMENTAR !!!!
    }

    // Jah implementado, nao alterar...
    public void consumir(int total_de_itens, int tempo_de_producao, int num_threads) {
        try {
// IMPLEMENTAR !!!!
        // Aceitar a conexao de entrada (listener) a atribui o novo socket de conexao para socket.
        	Socket socket = listener.accept();
// IMPLEMENTAR !!!!

            // Cria um Scanner para ler a entrada.
            this.in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int itens_por_thread = total_de_itens / num_threads;
        int thread_com_1_item_adicional = total_de_itens % num_threads;
        for (int t = 0; t < num_threads; t++) {
            final int itens_para_consumir = itens_por_thread + (t < thread_com_1_item_adicional ? 1 : 0);
            Consumir c = new Consumir(String.valueOf(t+1), itens_para_consumir, tempo_de_producao, this.in);
            consumidores.add(c);
        }
        for (Consumir c : consumidores) {
            c.start();
        }
    };

    public class Consumir extends Thread {
        private int total_itens;
        private int tempo_producao;
        private Scanner in;

        Consumir(String nome, int qtd, int ms, Scanner in) {
            super(nome);
            this.in = in;
            this.total_itens = qtd;
            this.tempo_producao = ms;
        }

        @Override
        public void run() {
            for (int i = 0; i < total_itens; i++) {

// IMPLEMENTAR !!!!
        // Ler uma linha do socket representando um produto. Uma linha = um produto.
        // Atencao, o socket eh um objeto critico e precisa de protecao contra acesso simultaneo.
            	InputStream produto = in.readUTF();
// IMPLEMENTAR !!!!

                // Simulando o tempo de consumo...
                try {
                    sleep(tempo_producao);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Main.itemConsumido(this.getName()); // <-- Nao alterar! Esta funcao imprime uma saida que sera verificada.
            }
        }
    }
}
