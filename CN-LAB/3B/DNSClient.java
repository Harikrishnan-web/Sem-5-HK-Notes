import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {

        DatagramSocket clientSocket = new DatagramSocket();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Domain Name: ");
        String domain = sc.nextLine();

        byte[] sendData = domain.getBytes();
        byte[] receiveData = new byte[1024];

        InetAddress serverIP = InetAddress.getByName("localhost");

        DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length,
                        serverIP, 9876);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String result =
                new String(receivePacket.getData(), 0,
                        receivePacket.getLength());

        System.out.println("IP Address: " + result);

        clientSocket.close();
    }
}
