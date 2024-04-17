package com.chatbot.stub;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Stub {
    private HashMap<String, Socket> stubMapSocket = new HashMap<>();
    private HashMap<String, PrintWriter> printWriters = new HashMap<>();
    private HashMap<String, BufferedReader> bufferedReaders = new HashMap<>();
    private HashMap<String, JSONObject> socketsJsonInfo = new HashMap<>();

    public Stub() throws IOException {
        loadAddressList();
    }

    private Socket getSocket(String nome) throws IOException {
        return stubMapSocket.computeIfAbsent(nome, key -> {
            try {
                JSONObject socketObject = socketsJsonInfo.get(nome);
                String url = socketObject.getString("url");
                String ip = socketObject.getString("ip");
                int port = socketObject.getInt("port");
                return new Socket(ip, port);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    private PrintWriter getPrintWriter(String nome) throws IOException {
        return printWriters.computeIfAbsent(nome, key -> {
            try {
                Socket socket = getSocket(nome);
                return new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                // Handle exception appropriately
                e.printStackTrace();
                return null;
            }
        });
    }

    private BufferedReader getBufferedReader(String nome) throws IOException {
        return bufferedReaders.computeIfAbsent(nome, key -> {
            try {
                Socket socket = getSocket(nome);
                return new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                // Handle exception appropriately
                e.printStackTrace();
                return null;
            }
        });
    }

    private void stopConnection(String nome) throws IOException {
        Socket socket = getSocket(nome);
        PrintWriter out = getPrintWriter(nome);
        BufferedReader in = getBufferedReader(nome);

        in.close();
        out.close();
        socket.close();

        printWriters.remove(nome);
        bufferedReaders.remove(nome);
        stubMapSocket.remove(nome);
    }

    public void loadAddressList() throws IOException {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("client/chatbotclient/src/main/java/com/chatbot/stub/sockets.json")));
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

    public String sendMessage(String msg) throws IOException {
        Socket socket = getSocket("chatbot");
        PrintWriter out = getPrintWriter("chatbot");
        BufferedReader in = getBufferedReader("chatbot");

        out.println(msg);
        String resp = in.readLine();
        if (msg.equals("good bye")) {
            stopConnection("chatbot");
        }
        return resp;
    }


}

