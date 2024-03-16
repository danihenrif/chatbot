package com.chatbot.rmi;

import com.chatbot.chatbot.Chatbot;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRmiChatbot {
    public static void main(String[] args) {
        try {
            MessengerService server = new MessengerServiceImpl();
            MessengerService stub = (MessengerService) UnicastRemoteObject.exportObject((MessengerService) server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("MessengerService", stub);
        } catch (Exception e){

        }
    }
}
