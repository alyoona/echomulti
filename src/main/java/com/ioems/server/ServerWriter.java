package com.ioems.server;

import java.io.*;

class ServerWriter {
    private BufferedWriter socketWriter;

    ServerWriter(BufferedWriter socketWriter) {
        this.socketWriter = socketWriter;
    }

    void sendMessageToClient(String message) {
        try {
            socketWriter.write(message);
            socketWriter.newLine();
            socketWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error while sending message to client: ", e);
        }
    }
}
