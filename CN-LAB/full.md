## 2) HTTP Web Client Program

### AIM

To write a Java program that acts as an HTTP client and retrieves a web page from a web server.

### ALGORITHM

1. Start the program.
2. Enter the website URL.
3. Create a connection to the URL.
4. Send a GET request to the server.
5. Read the response.
6. Display the response.
7. Close the connection.
8. Stop the program.

### JAVA PROGRAM

```java
import java.net.*;
import java.io.*;

class HTTPClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://example.com");

        HttpURLConnection con =
            (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
            new InputStreamReader(con.getInputStream()));

        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
        con.disconnect();
    }
}
```

### OUTPUT

```text
<!doctype html>
<html>
<head>
    <title>Example Domain</title>
</head>
<body>
    <div>
        <h1>Example Domain</h1>
        <p>This domain is for use in illustrative examples...</p>
    </div>
</body>
</html>
```

### RESULT

Thus, the Java program was successfully executed and the web page was retrieved from the HTTP server using an HTTP client.

### VIVA

**HTTP:** HyperText Transfer Protocol, used for communication between web clients and servers.

**Web Client:** A program that sends requests to a web server and receives responses.

**GET:** Used to request or retrieve data from a server.

---

## 3A and B
## 3) Echo Client and Echo Server Application Using TCP

### AIM

To write a Java program to implement an Echo Client and Echo Server using TCP.

### ALGORITHM — SERVER

1. Start the server.
2. Create a server socket.
3. Wait for the client to connect.
4. Receive a message from the client.
5. Send the same message back to the client.
6. Close the connection.
7. Stop the server.

### JAVA PROGRAM — SERVER

```java
import java.net.*;
import java.io.*;

class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);

        Socket s = ss.accept();

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        String msg = in.readLine();

        System.out.println("Client: " + msg);
        out.println(msg);

        s.close();
        ss.close();
    }
}
```

### ALGORITHM — CLIENT

1. Start the client.
2. Connect to the server using its IP address and port number.
3. Enter a message.
4. Send the message to the server.
5. Receive the same message from the server.
6. Display the received message.
7. Close the connection.
8. Stop the client.

### JAVA PROGRAM — CLIENT

```java
import java.net.*;
import java.io.*;

class EchoClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));

        System.out.print("Enter message: ");
        String msg = br.readLine();

        out.println(msg);

        String reply = in.readLine();

        System.out.println("Server: " + reply);

        s.close();
    }
}
```

### OUTPUT — SERVER

```text
Client: Hello Server
```

### OUTPUT — CLIENT

```text
Enter message: Hello Server
Server: Hello Server
```

### RESULT

Thus, the Echo Client and Echo Server application was successfully implemented using TCP.

### VIVA

**What is TCP?**
TCP is a connection-oriented protocol that provides reliable communication between two devices.

**What is an Echo Server?**
An Echo Server sends back the same message received from the client.

**What is a Socket?**
A socket is an endpoint used for communication between client and server.

**Why is port 5000 used?**
Port 5000 is used as an example port for communication between the client and server.
---
## 4) Simulation of Working of DNS Using Client-Server Connection

### AIM

To simulate the working of DNS using a client-server connection, where the client sends a domain name and the server returns its corresponding IP address.

### ALGORITHM — SERVER

1. Start the server.
2. Create a server socket.
3. Store domain names and IP addresses.
4. Accept the client connection.
5. Receive the domain name.
6. Search for the domain name.
7. If found, send the IP address.
8. Otherwise, send "DNS does not exist".
9. Close the connection.

### JAVA PROGRAM — SERVER

```java
import java.net.*;
import java.io.*;
import java.util.*;

class DNSServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);

        HashMap<String, String> dns = new HashMap<>();
        dns.put("google.com", "142.250.195.14");
        dns.put("yahoo.com", "98.137.11.163");
        dns.put("facebook.com", "157.240.241.35");

        Socket s = ss.accept();

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        String domain = in.readLine();

        if (dns.containsKey(domain))
            out.println(dns.get(domain));
        else
            out.println("DNS does not exist");

        s.close();
        ss.close();
    }
}
```

### ALGORITHM — CLIENT

1. Start the client.
2. Connect to the DNS server.
3. Enter a domain name.
4. Send the domain name to the server.
5. Receive the IP address.
6. Display the result.
7. Close the connection.

### JAVA PROGRAM — CLIENT

```java
import java.net.*;
import java.io.*;

class DNSClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        System.out.print("Enter domain name: ");
        String domain = br.readLine();

        out.println(domain);

        System.out.println("IP Address: " + in.readLine());

        s.close();
    }
}
```

### OUTPUT

```text
Enter domain name: google.com
IP Address: 142.250.195.14
```

```text
Enter domain name: abcxyz.com
IP Address: DNS does not exist
```

### RESULT

Thus, the working of DNS was successfully simulated using a client-server connection.

### VIVA

**What is DNS?**
DNS stands for Domain Name System. It converts domain names into IP addresses.

**What does the client send?**
The client sends a domain name to the server.

**What does the server return?**
The server returns the corresponding IP address or `"DNS does not exist"`.

**What is used to store domain names and IP addresses?**
`HashMap` is used.

Done — from now on I'll keep the programs **clean, short, and without unnecessary comments or divider lines**.
---

