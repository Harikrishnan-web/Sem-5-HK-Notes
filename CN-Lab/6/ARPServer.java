import java.io.*;
import java.net.*;
import java.util.HashMap;

public class ARPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("ARP Server is running...");

            HashMap<String, String> arpTable = new HashMap<>();

            arpTable.put("192.168.1.1", "AA:BB:CC:DD:EE:01");
            arpTable.put("192.168.1.2", "AA:BB:CC:DD:EE:02");
            arpTable.put("192.168.1.3", "AA:BB:CC:DD:EE:03");
            arpTable.put("192.168.1.4", "AA:BB:CC:DD:EE:04");

            Socket socket = serverSocket.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String ipAddress = in.readLine();

            System.out.println("Requested IP Address: " + ipAddress);

            String macAddress = arpTable.getOrDefault(
                    ipAddress, "MAC Address Not Found");

            out.println(macAddress);

            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}