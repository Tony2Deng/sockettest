package com.tony.server.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    private static final String serverName = "localhost";

    private static final int serverPort = 11000;

    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(serverPort);
            DatagramPacket packet = new DatagramPacket(new byte[2048], 2048);
            server.receive(packet);
            String message = new String(packet.getData()).trim();
            packet.setAddress(InetAddress.getLocalHost());
            while (!message.contains("by")) {
                System.out.println("[" + packet.getAddress().getHostName() + "_" + packet.getPort() + "]:" + message);
                packet.setData(message.toUpperCase().getBytes(StandardCharsets.UTF_8));
                server.send(packet);
                server.receive(packet);
                message = new String(packet.getData());
            }
            packet.setData("by".getBytes(StandardCharsets.UTF_8));
            server.send(packet);
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
