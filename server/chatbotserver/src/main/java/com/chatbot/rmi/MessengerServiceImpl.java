package com.chatbot.rmi;

import com.chatbot.chatbot.Chatbot;

public class MessengerServiceImpl implements MessengerService {

    private Chatbot chatbot;

    MessengerServiceImpl() {
        chatbot = new Chatbot();
        chatbot.start();
    }

    @Override
    public String sendMessage(String clientMessage) {
        return "Server: " + chatbot.processMessage(clientMessage);
    }

    public String unexposedMethod() { return "";}
}