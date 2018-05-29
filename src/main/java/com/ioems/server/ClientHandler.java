package com.ioems.server;

import java.io.*;
import java.net.Socket;


class ClientHandler {

    private Socket clientSocket;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    Socket getClientSocket() {
        return clientSocket;
    }

    void handle() throws IOException {
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        ServerReader serverReader = new ServerReader(socketReader);
        ServerWriter serverWriter = new ServerWriter(socketWriter);
        String message = serverReader.readMessageFromClient();
        if ("bye".equals(message)) {
            serverWriter.sendMessageToClient(message);
            socketReader.close();
            socketWriter.close();
            clientSocket.close();
        } else {
            serverWriter.sendMessageToClient("echo" + message);
        }
    }

    boolean isProcessable() throws IOException {
        int countAvailableBytes = clientSocket.getInputStream().available();
        return countAvailableBytes > 0;
    }
}
