import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RARPServer {

    public static void main(String[] args) {

        HashMap<String, String> rarpTable = new HashMap<>();

        rarpTable.put("AA:BB:CC:DD:EE:01", "192.168.1.1");
        rarpTable.put("AA:BB:CC:DD:EE:02", "192.168.1.2");
        rarpTable.put("AA:BB:CC:DD:EE:03", "192.168.1.3");
        rarpTable.put("AA:BB:CC:DD:EE:04", "192.168.1.4");

        try (ServerSocket serverSocket = new ServerSocket(5001)) {

            System.out.println("RARP Server is running...");
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

                String macAddress = in.readLine();

                System.out.println("Requested MAC Address: " + macAddress);

                String ipAddress = rarpTable.getOrDefault(
                    macAddress,
                    "IP Address Not Found"
                );

                out.println(ipAddress);

                System.out.println("IP Address sent to client: " + ipAddress);
            }

        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}