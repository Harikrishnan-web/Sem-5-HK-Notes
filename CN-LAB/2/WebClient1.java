import java.io.*;
import java.net.*;

public class WebClient1 {
    public static void main(String[] args) {
        try {
            // Connect to example.com on port 80
            Socket clientSocket = new Socket("example.com", 80);

            // Use OutputStreamWriter with auto-flush enabled
            PrintWriter writer = new PrintWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()), true
            );

            // Send HTTP GET request properly
            writer.println("GET / HTTP/1.1");
            writer.println("Host: example.com");
            writer.println("Connection: close");
            writer.println(); // Blank line to end headers

            // Read response from server
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Close resources
            reader.close();
            writer.close();
            clientSocket.close();

        } catch (IOException e) {
            System.out.println("Error connecting to the server");
            e.printStackTrace();
        }
    }
}
