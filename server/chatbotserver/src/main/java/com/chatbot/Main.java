package com.chatbot;

import com.chatbot.chatbot.Chatbot;
import com.chatbot.rmi.ServerRmiChatbot;
import com.chatbot.socket.ServerSocketChatbot;

public class Main {
    public static void main(String[] args) {
        Chatbot.addAiml();

        if (args[1].equals("socket")) {
            ServerSocketChatbot.main(args);
        }

        if (args[1].equals("rmi")){
            ServerRmiChatbot.main(args);
        }
    }
}