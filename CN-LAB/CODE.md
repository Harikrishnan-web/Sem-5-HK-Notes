## EX NO. 1 – Networking Commands

| **Command**                        | **Use (Purpose)**                                                | **What it Displays**                                                                                                                              |
| ---------------------------------- | ---------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| `ipconfig`                         | Displays the basic TCP/IP network configuration of the computer. | IP address, Subnet Mask, and Default Gateway.                                                                                                     |
| `ipconfig /all`                    | Displays complete network configuration details.                 | Host name, MAC (Physical) address, IP address, Subnet Mask, Default Gateway, DNS Server, DHCP Server, DHCP status, and other adapter information. |
| `ipconfig /renew`                  | Requests a new IP address from the DHCP server.                  | Assigns and displays the newly obtained IP address and updates the network configuration.                                                         |
| `ipconfig /release`                | Releases the current DHCP-assigned IP address.                   | Removes the current IP address from the network adapter until a new one is assigned.                                                              |
| `netstat`                          | Displays active network connections.                             | Active TCP connections between the local computer and remote hosts.                                                                               |
| `netstat -a`                       | Displays all active connections and listening ports.             | Active TCP/UDP connections and all listening ports.                                                                                               |
| `netstat -n`                       | Displays connections in numerical format.                        | IP addresses and port numbers without resolving host names.                                                                                       |
| `netstat -r`                       | Displays the routing table.                                      | Routing table showing network destinations, gateways, interfaces, and metrics.                                                                    |
| `netstat -s`                       | Displays network protocol statistics.                            | Statistics for TCP, UDP, IPv4, IPv6, ICMP, and other network protocols.                                                                           |
| `nslookup <domain_name>`           | Queries the DNS server to resolve a domain name.                 | DNS server details and the IP address of the specified domain.                                                                                    |
| `nslookup <IP_address>`            | Performs a reverse DNS lookup.                                   | The domain name associated with the specified IP address (if available).                                                                          |
| `tracert <hostname or IP address>` | Traces the path taken by packets to a destination.               | List of intermediate routers (hops), response time for each hop, and the destination reached.                                                     |
| `ping <hostname or IP address>`    | Tests connectivity between the local system and a remote host.   | Reply status, packet loss statistics, round-trip time (RTT), and TTL (Time to Live) values.                                                       |

---
## **EX. NO. 2: HTTP WEB CLIENT PROGRAM**

### **AIM**

To create a simple Java HTTP web client program that connects to a web server and displays the web page content.

---

## **Simple Algorithm**

**Step 1:** Start the program.

**Step 2:** Connect to the web server (`example.com`) using port **80**.

**Step 3:** Create an output stream to send an HTTP request.

**Step 4:** Send an HTTP **GET** request to the server.

**Step 5:** Create an input stream to receive the server's response.

**Step 6:** Read the response line by line and display it on the screen.

**Step 7:** Close the input stream, output stream, and socket connection.

**Step 8:** Stop the program.

---

## **Simple Java Program**

```java
import java.io.*;
import java.net.*;

public class WebClient {

    public static void main(String[] args) {

        try {
            // Connect to the web server
            Socket socket = new Socket("example.com", 80);

            // Send HTTP request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("GET / HTTP/1.1");
            out.println("Host: example.com");
            out.println();

            // Read server response
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Close the connection
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Unable to connect to the server.");
        }
    }
}
```

> **Note:** Some modern websites redirect HTTP requests or require HTTPS. If `example.com` does not return the full page in your environment, the program is still correct for demonstrating basic HTTP socket communication.

---

# **How This Program Works (Easy Explanation)**

### **1. Import Packages**

```java
import java.io.*;
import java.net.*;
```

These packages provide classes for network communication and reading/writing data.

---

### **2. Create the Main Class**

```java
public class WebClient {
```

Creates a class named **WebClient**.

---

### **3. Start the Program**

```java
public static void main(String[] args)
```

Execution starts from the `main()` method.

---

### **4. Create a Socket**

```java
Socket socket = new Socket("example.com", 80);
```

This connects the client to the web server **example.com** using **port 80**, which is the default HTTP port.

---

### **5. Create Output Stream**

```java
PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
```

This creates an output stream to send data to the server.

---

### **6. Send HTTP Request**

```java
out.println("GET / HTTP/1.1");
out.println("Host: example.com");
out.println();
```

These lines send an HTTP **GET** request asking for the home page (`/`) of **example.com**.

The blank line indicates that the HTTP request is complete.

---

### **7. Create Input Stream**

```java
BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
```

This receives the response sent by the web server.

---

### **8. Read the Response**

```java
String line;

while ((line = in.readLine()) != null) {
    System.out.println(line);
}
```

The program reads the server response one line at a time and prints it on the console.

The output usually contains:

* HTTP status (e.g., `HTTP/1.1 200 OK`)
* Response headers
* HTML content of the web page

---

### **9. Close the Connection**

```java
in.close();
out.close();
socket.close();
```

Closes the input stream, output stream, and socket to free system resources.

---

### **10. Exception Handling**

```java
catch (IOException e) {
    System.out.println("Unable to connect to the server.");
}
```

If any network error occurs (such as no internet connection or the server being unavailable), the program displays an error message instead of crashing.

---

## **Expected Output (Example)**

```
HTTP/1.1 200 OK
Content-Type: text/html
Content-Length: ...

<!doctype html>
<html>
<head>
<title>Example Domain</title>
...
```

---

## **Result**

Thus, the Java HTTP web client program was developed using TCP socket communication and executed successfully. The web page content received from the server was displayed successfully.

---
# **EX. NO. 3A: ECHO CLIENT AND ECHO SERVER USING TCP SOCKETS**

## **AIM**

To create a Java program for Echo Client and Echo Server using TCP sockets.

---

# **Simple Algorithm**

## **Echo Server**

**Step 1:** Start the program.

**Step 2:** Create a server socket on port **6666**.

**Step 3:** Wait for the client to connect.

**Step 4:** Create input and output streams.

**Step 5:** Receive a message from the client.

**Step 6:** Send the same message (echo) back to the client.

**Step 7:** Repeat until **stop** is entered.

**Step 8:** Close the connection and stop the program.

---

## **Echo Client**

**Step 1:** Start the program.

**Step 2:** Connect to the server using **localhost** and port **6666**.

**Step 3:** Create input and output streams.

**Step 4:** Read a message from the user.

**Step 5:** Send the message to the server.

**Step 6:** Receive the echoed message from the server and display it.

**Step 7:** Repeat until **stop** is entered.

**Step 8:** Close the connection and stop the program.

---

# **Simple Echo Server Program**

```java
import java.io.*;
import java.net.*;

public class ChatServer {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(6666);
            System.out.println("Server started...");

            Socket socket = server.accept();
            System.out.println("Client connected.");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String message;

            while (true) {

                message = in.readUTF();
                System.out.println("Client: " + message);

                out.writeUTF(message);
                out.flush();

                if (message.equalsIgnoreCase("stop"))
                    break;
            }

            in.close();
            out.close();
            socket.close();
            server.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

---

# **Simple Echo Client Program**

```java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 6666);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            String message;

            while (true) {

                System.out.print("Enter Message: ");
                message = sc.nextLine();

                out.writeUTF(message);
                out.flush();

                System.out.println("Server: " + in.readUTF());

                if (message.equalsIgnoreCase("stop"))
                    break;
            }

            sc.close();
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

---

# **How the Echo Program Works**

### **Server**

* Creates a `ServerSocket` on port **6666**.
* Waits for a client using `accept()`.
* Receives a message using `readUTF()`.
* Sends the **same message** back using `writeUTF()`.
* Continues until **stop** is received.
* Closes all connections.

### **Client**

* Connects to the server.
* Reads a message from the keyboard.
* Sends it to the server.
* Receives the **same message** (echo) from the server.
* Displays it on the screen.
* Stops when **stop** is entered.

---

# **EX. NO. 3B: CHAT APPLICATION USING TCP SOCKETS**

## **AIM**

To create a Java Chat Application using TCP sockets.

---

# **Simple Algorithm**

## **Chat Server**

**Step 1:** Start the program.

**Step 2:** Create a server socket on port **6666**.

**Step 3:** Wait for the client to connect.

**Step 4:** Create input and output streams.

**Step 5:** Receive a message from the client.

**Step 6:** Enter a reply and send it to the client.

**Step 7:** Repeat until **stop** is entered.

**Step 8:** Close the connection and stop the program.

---

## **Chat Client**

**Step 1:** Start the program.

**Step 2:** Connect to the server.

**Step 3:** Create input and output streams.

**Step 4:** Enter a message and send it to the server.

**Step 5:** Receive the server's reply and display it.

**Step 6:** Repeat until **stop** is entered.

**Step 7:** Close the connection and stop the program.

---

# **Simple Chat Server Program**

```java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServer {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(6666);
            System.out.println("Server started...");

            Socket socket = server.accept();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            String message;

            while (true) {

                message = in.readUTF();
                System.out.println("Client: " + message);

                if (message.equalsIgnoreCase("stop"))
                    break;

                System.out.print("Reply: ");
                message = sc.nextLine();

                out.writeUTF(message);
                out.flush();

                if (message.equalsIgnoreCase("stop"))
                    break;
            }

            sc.close();
            in.close();
            out.close();
            socket.close();
            server.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

---

# **Simple Chat Client Program**

```java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 6666);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            String message;

            while (true) {

                System.out.print("You: ");
                message = sc.nextLine();

                out.writeUTF(message);
                out.flush();

                if (message.equalsIgnoreCase("stop"))
                    break;

                System.out.println("Server: " + in.readUTF());
            }

            sc.close();
            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

---

# **How the Chat Application Works**

### **Chat Server**

1. Creates a `ServerSocket` on port **6666**.
2. Waits for the client to connect.
3. Receives a message using `readUTF()`.
4. Displays the client's message.
5. Reads a reply from the keyboard.
6. Sends the reply using `writeUTF()`.
7. Repeats until **stop** is entered.
8. Closes the socket and streams.

---

### **Chat Client**

1. Connects to the server using `Socket`.
2. Takes a message from the user.
3. Sends it to the server using `writeUTF()`.
4. Receives the server's reply using `readUTF()`.
5. Displays the reply.
6. Continues chatting until **stop** is entered.
7. Closes all streams and the socket.

---

### **Difference Between Echo and Chat Applications**

| **Echo Application**                                                 | **Chat Application**                                                |
| -------------------------------------------------------------------- | ------------------------------------------------------------------- |
| The server sends back the **same message** received from the client. | The server sends its **own reply** to the client.                   |
| Used to test communication.                                          | Used for two-way communication.                                     |
| No user input is needed on the server after receiving a message.     | Both client and server type messages and communicate interactively. |

---
