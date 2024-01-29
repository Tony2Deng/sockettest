package com.tony.client.udp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPClient {
    private static final String serverName = "localhost";

    private static final int serverPort = 11000;

    private static final int clientPort = 12000;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String massage = scanner.nextLine();
            DatagramSocket client = new DatagramSocket(clientPort);
            DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
            packet.setPort(serverPort);
            packet.setAddress(InetAddress.getLocalHost());
            while (!"exit".equals(massage)) {
                packet.setData(massage.getBytes(StandardCharsets.UTF_8));
                client.send(packet);
                client.receive(packet);
                System.out.println("[" + packet.getAddress().getHostName() + "_" + packet.getPort() + "]:" + new String(packet.getData()).trim());
                massage = scanner.nextLine();
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
