package com.chatbot.chatbot;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;

public class Chatbot {

    private Bot bot;
    private Chat chatSession;

    private String resourcesPath;
    private static final boolean TRACE_MODE = false;
    static String botName = "super";


    public void start() {

        MagicBooleans.trace_mode = TRACE_MODE;
        resourcesPath = getResourcesPath();
        bot = new Bot(botName, resourcesPath);
        chatSession = new Chat(bot);
        bot.brain.nodeStats();
    }

    public String processMessage(String request) {
        try {
            String response = "";

            if (MagicBooleans.trace_mode)
                System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));

            response = chatSession.multisentenceRespond(request);

            while (response.contains("&lt;"))
                response = response.replace("&lt;", "<");
            while (response.contains("&gt;"))
                response = response.replace("&gt;", ">");
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
    String resourcesPath = path + File.separator + "server" + File.separator + "chatbotserver" +File.separator+ "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }

}
