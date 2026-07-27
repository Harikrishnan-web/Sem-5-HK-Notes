import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ARPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter IP Address: ");
            String ipAddress = sc.nextLine();

            out.println(ipAddress);

            String macAddress = in.readLine();

            System.out.println("MAC Address: " + macAddress);

            sc.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}