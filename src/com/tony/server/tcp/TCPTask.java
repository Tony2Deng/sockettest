package com.tony.server.tcp;

import com.tony.utils.TCPUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPTask implements Runnable {
    private final int num;

    private final Socket socket;

    public TCPTask(Socket socket, int num) {
        this.socket = socket;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = TCPUtils.getReader(socket);
            PrintWriter writer = TCPUtils.getWriter(socket);
            String msg = reader.readLine().trim();
            while (msg == null || msg.equals("")) {
                System.out.println("aa");
                msg = reader.readLine();
            }
            while (!"by".equalsIgnoreCase(msg)) {
                System.out.println("receive[" + num + "]: " + msg);
                writer.println("[" + num + "]" + msg.toUpperCase());
                msg = reader.readLine();
            }
            writer.println("by");
            System.out.println("stop connect num" + num);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
