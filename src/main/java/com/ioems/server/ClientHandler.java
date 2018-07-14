package com.ioems.server;

import java.io.*;
import java.net.Socket;

class ClientHandler implements AutoCloseable {

    private boolean closed = false;
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;

    ClientHandler(Socket clientSocket) {
        try {
            socketReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (Exception e) {
            throw new RuntimeException("error when initializing clientHandler",e);
        }
    }

    void handle() throws IOException {
        ServerReader serverReader = new ServerReader(socketReader);
        ServerWriter serverWriter = new ServerWriter(socketWriter);
        String message = serverReader.readMessageFromClient();
        if ("bye".equals(message)) {
            serverWriter.sendMessageToClient(message);
            try {
                close();
            } catch (Exception e) {
                throw new RuntimeException("error while closing client,", e);
            }
        } else {
            serverWriter.sendMessageToClient("echo" + message);
        }
    }

    boolean isProcessable() throws IOException {
        return socketReader.ready();
    }

    @Override
    public void close() throws Exception {
        socketReader.close();
        socketWriter.close();
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }
}