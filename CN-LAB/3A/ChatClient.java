import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        try {
            // Connect to server at localhost on port 6666
            Socket s = new Socket("localhost", 6666);
            System.out.println("Connected to server.");

            // Input and output streams for communication
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            Scanner input = new Scanner(System.in);

            String sendData = "";
            String receiveData = "";

            // Loop until user types "stop"
            while (!sendData.equalsIgnoreCase("stop")) {
                System.out.print("TO SERVER: ");
                sendData = input.nextLine();

                dout.writeUTF(sendData);
                dout.flush();

                if (sendData.equalsIgnoreCase("stop")) {
                    break;
                }

                receiveData = din.readUTF();
                System.out.println("SERVER SAYS: " + receiveData);
            }

            // Close resources
            din.close();
            dout.close();
            input.close();
            s.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
