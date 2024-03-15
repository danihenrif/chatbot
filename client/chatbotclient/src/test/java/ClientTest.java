import com.chatbot.Main;

public class ClientTest {
    public static void main(String[] args) {
        final int NUM_CLIENTS = 10; // Defina o número de clientes que deseja simular

        // Cria e inicia múltiplas threads de cliente
        for (int i = 0; i < NUM_CLIENTS; i++) {
            final int clientId = i;
            Thread clientThread = new Thread(() -> {
                System.out.println("Cliente " + clientId + " tentando se conectar...");
                Main.main(new String[0]); // Executa o código do cliente
            });
            clientThread.start();
        }
    }
}