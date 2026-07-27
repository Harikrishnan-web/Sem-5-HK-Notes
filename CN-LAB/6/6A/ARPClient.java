import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ARPClient {

    public static void main(String[] args) {

        try (
            Socket socket = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true
            );

            Scanner sc = new Scanner(System.in)
        ) {

            System.out.print("Enter IP Address: ");
            String ipAddress = sc.nextLine();

            out.println(ipAddress);

            String macAddress = in.readLine();

            System.out.println("MAC Address: " + macAddress);

        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}