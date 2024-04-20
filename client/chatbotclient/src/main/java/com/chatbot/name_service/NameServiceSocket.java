package com.chatbot.name_service;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class NameServiceSocket {

    private HashMap<String, JSONObject> socketsJsonInfo = new HashMap<>();
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            loadAddressList();
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                String regexGet = "^get\\s+(.*)$";
                String regexPost = "post .*";

                // Usando Pattern e Matcher para encontrar e extrair o componente desejado
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regexGet);
                java.util.regex.Matcher matcher = pattern.matcher(inputLine);

                System.out.println(inputLine);

                if (matcher.find()) {
                    String argument = matcher.group(1);
                    System.out.println(argument);

                    JSONObject socketObject = socketsJsonInfo.get(argument);
                    try {
                        String url = socketObject.getString("url");
                        String ip = socketObject.getString("ip");
                        int portservice = socketObject.getInt("port");
                        out.println("ip=" + ip + ";port=" + portservice);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }



                if (inputLine.matches(regexPost)) {
                    out.println("good bye");
                }
                out.println();
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
        NameServiceSocket server = new NameServiceSocket();
        server.start(1236);
    }


    public void loadAddressList() throws IOException {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("client/chatbotclient/src/main/java/com/chatbot/name_service/sockets.json")));
            JSONObject jsonObject = new JSONObject(jsonContent);
            JSONArray socketsArray = jsonObject.getJSONArray("sockets");
            for (int i = 0; i < socketsArray.length(); i++) {
                JSONObject socketObject = socketsArray.getJSONObject(i);

                // Extract socket details
                String nome = socketObject.getString("nome");
                socketsJsonInfo.put(nome, socketObject);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
