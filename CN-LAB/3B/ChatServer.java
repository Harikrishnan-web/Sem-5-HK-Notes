import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    public static void main(String[] args) {

        try (
            ServerSocket ss = new ServerSocket(6666);
            Scanner input = new Scanner(System.in)
        ) {
            System.out.println("Server started. Waiting for client...");

            Socket s = ss.accept();
            System.out.println("Client connected.");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            String sendData;
            String receiveData;

            while (true) {

                receiveData = din.readUTF();
                System.out.println("CLIENT SAYS: " + receiveData);

                if (receiveData.equalsIgnoreCase("stop")) {
                    break;
                }

                System.out.print("TO CLIENT: ");
                sendData = input.nextLine();

                dout.writeUTF(sendData);
                dout.flush();

                if (sendData.equalsIgnoreCase("stop")) {
                    break;
                }
            }

            din.close();
            dout.close();
            s.close();

            System.out.println("Connection closed.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}