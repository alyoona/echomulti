package com.ioems.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class ClientWriter {

    private BufferedReader consoleReader;
    private BufferedWriter socketWriter;

    ClientWriter(BufferedReader consoleReader, BufferedWriter socketWriter) {
        this.consoleReader = consoleReader;
        this.socketWriter = socketWriter;
    }

    void sendMessage() {
        try {
            String line = consoleReader.readLine();
            socketWriter.write(line);
            socketWriter.newLine();
            socketWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error while sending message to server, ", e);
        }
    }
}

