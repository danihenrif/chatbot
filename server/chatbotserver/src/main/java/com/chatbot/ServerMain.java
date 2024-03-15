package com.chatbot;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    static Chatbot chatbot = new Chatbot();
    public static void main(String[] args) {
        try {
            @SuppressWarnings("resource")
            ServerSocket servidor = new ServerSocket(1235);
            System.out.println("Servidor ouvindo a porta 1235");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                
                boolean continuaConversa = true;
                while(continuaConversa) {
                	String mensagem = (String) entrada.readObject();
                    System.out.println("Pergunta do cliente: " + mensagem);
           
	                if (mensagem.equals("q")) {
	                	String resposta = "Fechando a sua conexão...";
	                    saida.writeObject(resposta);
	                    saida.flush();
	                    
	                } else {
	                    String resposta = Chatbot.main(null, mensagem);
	                    saida.writeObject(resposta);
	                    saida.flush();
	                }
                }
                saida.close();
                entrada.close();
                cliente.close();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}