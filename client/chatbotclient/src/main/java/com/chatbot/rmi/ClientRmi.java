package com.chatbot.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientRmi {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            MessengerService server = (MessengerService) registry
                    .lookup("MessengerService");

            Scanner scanner = new Scanner(System.in);
            String pergunta = "";
            while (!pergunta.equals("good bye")) {
                System.out.println("Você: ");
                pergunta = scanner.nextLine();

                String response = server.sendMessage(pergunta);
                System.out.println(response);
            }
            scanner.close();
        } catch (Exception e) {
        }
    }
}
