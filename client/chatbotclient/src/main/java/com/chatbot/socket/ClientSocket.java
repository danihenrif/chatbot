package com.chatbot.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) {
        ClientSocket client = new ClientSocket();
        try {
            client.startConnection("localhost", 1235);

            Scanner scanner = new Scanner(System.in);
            String pergunta = "";

            while (!pergunta.equals("good bye")) {
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
