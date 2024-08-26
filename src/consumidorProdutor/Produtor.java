import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.*;

public class Produtor {
    // Lista com todas as instâncias de Produzir (cada uma rodando em uma Thread)
    private ArrayList<Produzir> produtores = new ArrayList<Produzir>();

    // Socket e PrintWriter para escrever no socket
    private Socket socket;
    private PrintWriter out;

    // Construtor, jah implementado... nao alterar!
    //gera a conexao com o socket!
    Produtor(String host, int porta) throws IOException {
// IMPLEMENTAR !!!!
        // Conectar com o servidor e atribuir a conexao para this.socket
    	Socket socket = new Socket(host, porta);
// IMPLEMENTAR !!!!
    }

    // Jah implementado, nao alterar...
    //starta a thread com os parametros que produzir recebe
    public void produzir(int total_de_itens, int tempo_de_producao, int num_threads) {
        try {
            // Criando um PrintWriter para escrever no socket.
            this.out = new PrintWriter(this.socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int itens_por_thread = total_de_itens / num_threads;
        int thread_com_1_item_adicional = total_de_itens % num_threads;
        for (int t = 0; t < num_threads; t++) {
            final int itens_para_produzir = itens_por_thread + (t < thread_com_1_item_adicional ? 1 : 0);
          Produzir p = new Produzir(String.valueOf(t+1), itens_para_produzir, tempo_de_producao, this.out);
            produtores.add(p);  
        }
        for (Produzir p : produtores) {
            p.start();
        }
    };
    
    //É a thread de fato com o que o codigo precisa fazer!
    public class Produzir extends Thread {
        private int total_itens;
        private int tempo_producao;
        private PrintWriter out;

        Produzir(String nome, int qtd, int ms, PrintWriter out) {
            super(nome);
            this.out = out;

            this.total_itens = qtd;
            this.tempo_producao = ms;
        }
        
        @Override
        public void run() {
            for (int i = 0; i < total_itens; i++) {
                // Simulando o tempo de producao...
                try {
                    sleep(tempo_producao);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Main.itemProduzido(this.getName()); // <-- Nao alterar! Esta funcao imprime uma saida que sera verificada.

// IMPLEMENTAR !!!!
        // Escrever uma linha no socket representando o produto criado. Uma linha = um produto.
        // PODE SER QUALQUER TEXTO, DESDE QUE SEJA UMA LINHA.
        // Atencao, o socket eh um objeto critico e precisa de protecao contra acesso simultaneo.
                
// IMPLEMENTAR !!!!
            }

        }
    }
}
