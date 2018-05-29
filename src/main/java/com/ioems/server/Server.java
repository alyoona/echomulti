package com.ioems.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        List<ClientHandler> list = Collections.synchronizedList(new ArrayList<>());
        Runnable clientHandlerListener = new ClientHandlerListener(list);
        Thread thread = new Thread(clientHandlerListener);
        thread.start();
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            synchronized (list) {
                list.add(clientHandler);
            }
        }
    }
}