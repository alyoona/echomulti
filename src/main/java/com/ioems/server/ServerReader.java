package com.ioems.server;

import java.io.*;

class ServerReader {

    ServerReader(BufferedReader socketReader) {
        this.socketReader = socketReader;
    }

    private BufferedReader socketReader;

    String readMessageFromClient() {
        try {
           return socketReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error while reading message from client", e);
        }
    }
}
