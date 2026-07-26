import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {

    public static void main(String[] args) {

        try {
            Socket s = new Socket("localhost", 6666);
            System.out.println("Connected to server.");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            Scanner input = new Scanner(System.in);

            String sendData = "";
            String receiveData = "";
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

            din.close();
            dout.close();
            input.close();
            s.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
