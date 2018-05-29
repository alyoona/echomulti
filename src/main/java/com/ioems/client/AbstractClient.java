package com.ioems.client;

import java.io.*;
import java.net.Socket;

public abstract class AbstractClient {

    public void start() {
       try (
        Socket clientSocket = new Socket("localhost", 3000);
        BufferedReader consoleReader =
                new BufferedReader
                        (new InputStreamReader
                                (System.in));
        BufferedWriter socketWriter =
                new BufferedWriter
                        (new OutputStreamWriter
                                (clientSocket.getOutputStream()));
        BufferedReader socketReader =
                new BufferedReader
                        (new InputStreamReader
                                (clientSocket.getInputStream()))
       ) {
           ClientWriter clientWriter = new ClientWriter(consoleReader, socketWriter);
           ClientReader clientReader = new ClientReader(socketReader);
           while (true) {
               clientWriter.sendMessage();
               clientReader.printAnswerFromServer();
           }
       } catch (IOException e) {
           throw new RuntimeException("Error while starting client, ", e);
       }
    }
}
