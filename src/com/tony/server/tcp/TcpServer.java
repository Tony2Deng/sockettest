package com.tony.server.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    private static ServerSocket serverSocket;

    private static final int serverPort = 12000;

    public static void main(String[] args) {
        getTCPServer();
        try {
            int i = 0;
            while (true) {
                Socket tcpConnect = serverSocket.accept();
                TCPTask tcpTask = new TCPTask(tcpConnect, i++);
                new Thread(tcpTask).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getTCPServer() {
        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server listen port:" + serverPort);
    }
}
