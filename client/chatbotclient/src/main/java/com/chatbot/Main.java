package com.chatbot;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Socket clienteSocket = new Socket("localhost", 1235);
            
            ObjectOutputStream saida = new ObjectOutputStream(clienteSocket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(clienteSocket.getInputStream());
            
			try (Scanner scanner = new Scanner(System.in)) {
				String pergunta = "";
				
				while(!pergunta.equals("q")) {
					System.out.println("Você: ");
					pergunta = scanner.nextLine();
					
					saida.writeObject(pergunta);
					saida.flush();

				    String resposta = (String) entrada.readObject();
				    System.out.println("Chatbot: " + resposta);

				}

			    saida.close();
			    entrada.close();
			    clienteSocket.close();
			}
	    } 
        catch (Exception e) {
        	System.out.println("Erro: " + e.getMessage());
	    }
    }
}