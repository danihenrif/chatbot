package com.chatbot.socket;

import com.chatbot.chatbot.Chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketChatbot {
    private Chatbot chatbot;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            chatbot = new Chatbot();
            chatbot.start();
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                if (".".equals(inputLine)) {
                    out.println("good bye");
                    break;
                }
                out.println(chatbot.processMessage(inputLine));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServerSocketChatbot server = new ServerSocketChatbot();
        server.start(1235);
    }
}

