package com.ioems.client;

import java.io.BufferedReader;
import java.io.IOException;

class ClientReader {

    private BufferedReader socketReader;

    ClientReader(BufferedReader socketReader) {
        this.socketReader = socketReader;
    }

    void printAnswerFromServer() {
        try {
            String message = socketReader.readLine();
            if ("bye".equals(message)) {
                System.out.println("Server answer: " + message);
                System.out.println("For further sending messages please reconnect");
            } else {
                System.out.println("Server answer: " + message);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error while reading message from server, ", e);
        }
    }
}
