package com.chatbot;

import com.chatbot.rmi.ClientRmi;
import com.chatbot.socket.ClientSocket;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        if (args[1].equals("socket")) {
            ClientSocket.main(args);
        }

        if (args[1].equals("rmi")){
            ClientRmi.main(args);
        }

    }
}