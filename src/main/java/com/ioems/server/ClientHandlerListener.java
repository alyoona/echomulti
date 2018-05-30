package com.ioems.server;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

class ClientHandlerListener implements Runnable {
    private List<ClientHandler> clientList;

    ClientHandlerListener(List<ClientHandler> clientList) {
        this.clientList = clientList;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (clientList) {
                Iterator<ClientHandler> iterator = clientList.iterator();
                while (iterator.hasNext()) {
                    ClientHandler client = iterator.next();
                    if (!client.isClosed()) {
                        try {
                            if (client.isProcessable()) {
                                client.handle();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        iterator.remove();
                        // Exception in thread "Thread-0" java.util.ConcurrentModificationException
                    }
                }
            }
        }
    }
}




