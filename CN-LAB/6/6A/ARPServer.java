import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ARPServer {

    public static void main(String[] args) {

        HashMap<String, String> arpTable = new HashMap<>();

        arpTable.put("192.168.1.1", "AA:BB:CC:DD:EE:01");
        arpTable.put("192.168.1.2", "AA:BB:CC:DD:EE:02");
        arpTable.put("192.168.1.3", "AA:BB:CC:DD:EE:03");
        arpTable.put("192.168.1.4", "AA:BB:CC:DD:EE:04");

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            System.out.println("ARP Server is running...");
            System.out.println("Waiting for client connection...");

            try (
                Socket socket = serverSocket.accept();

                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );

                PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true
                )
            ) {

                String ipAddress = in.readLine();

                System.out.println("Requested IP Address: " + ipAddress);

                String macAddress = arpTable.getOrDefault(
                    ipAddress,
                    "MAC Address Not Found"
                );

                out.println(macAddress);

                System.out.println("MAC Address sent to client: " + macAddress);
            }

        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}