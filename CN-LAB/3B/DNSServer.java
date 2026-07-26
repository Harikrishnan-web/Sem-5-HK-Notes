import java.net.*;
import java.util.HashMap;

public class DNSServer {
    public static void main(String[] args) throws Exception {

        DatagramSocket serverSocket = new DatagramSocket(9876);

        HashMap<String, String> dnsTable = new HashMap<>();
        dnsTable.put("www.google.com", "142.250.183.14");
        dnsTable.put("www.yahoo.com", "98.137.11.163");
        dnsTable.put("www.facebook.com", "157.240.229.35");
        dnsTable.put("www.amazon.com", "205.251.242.103");

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("DNS Server is running...");

        while (true) {

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            serverSocket.receive(receivePacket);

            String domain =
                    new String(receivePacket.getData(), 0,
                            receivePacket.getLength());

            System.out.println("Query Received: " + domain);

            String ipAddress =
                    dnsTable.getOrDefault(domain, "Domain Not Found");

            sendData = ipAddress.getBytes();

            InetAddress clientIP = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length,
                            clientIP, clientPort);

            serverSocket.send(sendPacket);
        }
    }
}
