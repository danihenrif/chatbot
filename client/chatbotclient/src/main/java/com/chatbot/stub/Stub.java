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

    public Stub() throws IOException {
    }

    private Socket getSocket(String nome) throws IOException {
        return stubMapSocket.computeIfAbsent(nome, key -> {
            try {

                Socket clientNamingServiceSocket = new Socket("localhost", 1236);

                PrintWriter out = new PrintWriter(clientNamingServiceSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientNamingServiceSocket.getInputStream()));
                String message = String.format("get %s", nome);
                out.println(message);
                String resp = in.readLine();

                System.out.println(resp);
                String[] keyValuePairs = resp.split(";");

                String[] keyAndValueIP = keyValuePairs[0].split("=");
                String ip = keyAndValueIP[1];

                String[] keyAndValuePORT = keyValuePairs[1].split("=");
                int port = Integer.parseInt(keyAndValuePORT[1].trim());

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

