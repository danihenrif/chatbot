package com.chatbot;


import com.chatbot.stub.Stub;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Stub stub = null;
        try {
            stub = new Stub();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Scanner scanner = new Scanner(System.in);
            String pergunta = "";

            while (!pergunta.equals("good bye")) {
                System.out.println("Você: ");
                pergunta = scanner.nextLine();

                String response = stub.sendMessage(pergunta);
                System.out.println("Chatbot: " + response);

            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}