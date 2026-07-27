import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RARPClient {

    public static void main(String[] args) {

        try (
            Socket socket = new Socket("localhost", 5001);

            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true
            );

            Scanner sc = new Scanner(System.in)
        ) {

            System.out.print("Enter MAC Address: ");
            String macAddress = sc.nextLine();

            out.println(macAddress);

            String ipAddress = in.readLine();

            System.out.println("IP Address: " + ipAddress);

        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}