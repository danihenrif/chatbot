package com.chatbot;

import com.chatbot.socket.ClientSocket;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientSocket client = new ClientSocket();
        try {
            client.startConnection("localhost", 1235);

            Scanner scanner = new Scanner(System.in);
            String pergunta = "";

            while (!pergunta.equals(".")) {
                System.out.println("Você: ");
                pergunta = scanner.nextLine();

                String response = client.sendMessage(pergunta);
                System.out.println("Chatbot: " + response);

            }
            client.stopConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}