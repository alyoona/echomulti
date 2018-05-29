package com.ioems.server;

import java.io.IOException;
import java.util.List;

class ClientHandlerListener implements Runnable {
    private List<ClientHandler> clientList;

    ClientHandlerListener(List<ClientHandler> clientList) {
        this.clientList = clientList;
    }

    @Override
    public void run() {
        while (true) {
            if (clientList.size() > 0) {
                System.out.println("count client added: " + clientList.size());
                break;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("out of waiting client loop");
        while (true) {
            synchronized (clientList) {
                int socketIndex = 0;
                for (ClientHandler client : clientList) {
                    if (!client.getClientSocket().isClosed()) {
                        try {
                            if (client.isProcessable()) {
                                client.handle();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        socketIndex++;
                    } else {
                        clientList.remove(socketIndex);
                        // Exception in thread "Thread-0" java.util.ConcurrentModificationException
                        socketIndex--;
                    }
                }
            }
        }
    }
}





