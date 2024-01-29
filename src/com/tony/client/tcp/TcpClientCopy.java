package com.tony.client.tcp;

import com.tony.utils.TCPUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClientCopy {
    private static final String serverName = "127.0.0.1";

    private static final int serverPort = 12000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket(serverName, serverPort);
            PrintWriter writer = TCPUtils.getWriter(socket);
            BufferedReader reader = TCPUtils.getReader(socket);
            String msg = scanner.nextLine();
            while (!"exit".equalsIgnoreCase(msg)) {
                writer.println(msg);
                String record = reader.readLine();
                System.out.println("client receive: " + record);
                msg = scanner.nextLine();
            }
            writer.println("by");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
