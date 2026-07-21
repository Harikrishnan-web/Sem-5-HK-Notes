import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {

    public static void main(String[] args) {
        try {
            
            ServerSocket ss = new ServerSocket(6666);
            System.out.println("Server started. Waiting for client...");

           
            Socket s = ss.accept();
            System.out.println("Client connected.");

            
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            Scanner input = new Scanner(System.in);

            String sendData = "";
            String receiveData = "";

            while (!receiveData.equalsIgnoreCase("stop")) {
                receiveData = din.readUTF();
                System.out.println("CLIENT SAYS: " + receiveData);

                if (receiveData.equalsIgnoreCase("stop")) {
                    break;
                }

                System.out.print("TO CLIENT: ");
                sendData = input.nextLine();

                dout.writeUTF(sendData);
                dout.flush();
            }

            din.close();
            dout.close();
            input.close();
            s.close();
            ss.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
