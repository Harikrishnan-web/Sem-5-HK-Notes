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
## 6) Simulation of ARP and RARP

### AIM

To simulate the working of ARP and RARP using client-server communication.

### ALGORITHM — SERVER

1. Start the server.
2. Store IP-MAC and MAC-IP address mappings.
3. Accept the client connection.
4. Receive the request from the client.
5. Search the mapping.
6. Send the corresponding address.
7. Close the connection.

### JAVA PROGRAM — SERVER

```java
import java.net.*;
import java.io.*;
import java.util.*;

class ARPRARPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);

        HashMap<String, String> arp = new HashMap<>();
        arp.put("192.168.1.1", "AA:BB:CC:DD:EE:01");
        arp.put("192.168.1.2", "AA:BB:CC:DD:EE:02");
        arp.put("192.168.1.3", "AA:BB:CC:DD:EE:03");

        Socket s = ss.accept();

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        String type = in.readLine();
        String value = in.readLine();

        if (type.equals("ARP")) {
            out.println(arp.getOrDefault(value, "ARP entry not found"));
        } else {
            String result = "RARP entry not found";
            for (Map.Entry<String, String> e : arp.entrySet()) {
                if (e.getValue().equals(value))
                    result = e.getKey();
            }
            out.println(result);
        }

        s.close();
        ss.close();
    }
}
```

### ALGORITHM — CLIENT

1. Start the client.
2. Connect to the server.
3. Select ARP or RARP.
4. Enter the required address.
5. Send the request to the server.
6. Receive and display the result.
7. Close the connection.

### JAVA PROGRAM — CLIENT

```java
import java.net.*;
import java.io.*;

class ARPRARPClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));

        BufferedReader in = new BufferedReader(
            new InputStreamReader(s.getInputStream()));

        PrintWriter out = new PrintWriter(
            s.getOutputStream(), true);

        System.out.print("Enter ARP or RARP: ");
        String type = br.readLine();

        System.out.print("Enter address: ");
        String value = br.readLine();

        out.println(type);
        out.println(value);

        System.out.println("Result: " + in.readLine());

        s.close();
    }
}
```

### OUTPUT — ARP

```text
Enter ARP or RARP: ARP
Enter address: 192.168.1.1
Result: AA:BB:CC:DD:EE:01
```

### OUTPUT — RARP

```text
Enter ARP or RARP: RARP
Enter address: AA:BB:CC:DD:EE:02
Result: 192.168.1.2
```

### RESULT

Thus, ARP and RARP were successfully simulated using client-server communication.

### VIVA

**ARP:** Converts an IP address into a MAC address.

**RARP:** Converts a MAC address into an IP address.

**ARP:** IP → MAC

**RARP:** MAC → IP
---
## 9A) Simulation of Distance Vector Routing

### AIM

To simulate Distance Vector Routing using the Bellman-Ford algorithm to find the shortest path between nodes.

### ALGORITHM

1. Read the number of nodes and cost matrix.
2. Initialize the distance from the source.
3. Relax all edges repeatedly.
4. Update the shortest distance.
5. Repeat for `n-1` times.
6. Display the shortest distances.

### JAVA PROGRAM

```java id="dvmain"
import java.util.*;

class DistanceVector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];

        System.out.println("Enter cost matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();

        System.out.print("Enter source node: ");
        int src = sc.nextInt();

        int[] dist = new int[n];
        Arrays.fill(dist, 999);
        dist[src] = 0;

        for (int k = 0; k < n - 1; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (cost[i][j] != 0 && dist[i] + cost[i][j] < dist[j])
                        dist[j] = dist[i] + cost[i][j];

        System.out.println("Shortest distances:");
        for (int i = 0; i < n; i++)
            System.out.println(src + " -> " + i + " = " + dist[i]);
    }
}
```

### OUTPUT

```text
Enter number of nodes: 4
Enter cost matrix:
0 2 5 0
2 0 1 4
5 1 0 2
0 4 2 0
Enter source node: 0

Shortest distances:
0 -> 0 = 0
0 -> 1 = 2
0 -> 2 = 3
0 -> 3 = 5
```

### RESULT

Thus, Distance Vector Routing was successfully simulated using the Bellman-Ford algorithm to find the shortest paths.

### VIVA

**Distance Vector Routing:** Each router maintains the distance to other routers and updates it using information from neighbouring routers.

**Bellman-Ford:** Finds the shortest path by repeatedly relaxing all edges.

**Remember:** Distance Vector → **Bellman-Ford → Relax edges**

## 9B) Simulation of Link State Routing

### AIM

To simulate Link State Routing and find the shortest path between nodes using Dijkstra's algorithm.

### ALGORITHM

1. Read the number of nodes and cost matrix.
2. Select the source node.
3. Set source distance to 0 and others to infinity.
4. Select the unvisited node with the smallest distance.
5. Update the distances of neighbouring nodes.
6. Mark the node as visited.
7. Repeat until all nodes are visited.
8. Display the shortest distances.

### JAVA PROGRAM

```java id="lsmain"
import java.util.*;

class LinkState {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];

        System.out.println("Enter cost matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost[i][j] = sc.nextInt();

        System.out.print("Enter source node: ");
        int src = sc.nextInt();

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, 999);
        dist[src] = 0;

        for (int k = 0; k < n; k++) {
            int u = -1;

            for (int i = 0; i < n; i++)
                if (!visited[i] && (u == -1 || dist[i] < dist[u]))
                    u = i;

            visited[u] = true;

            for (int v = 0; v < n; v++)
                if (cost[u][v] != 0 &&
                    dist[u] + cost[u][v] < dist[v])
                    dist[v] = dist[u] + cost[u][v];
        }

        System.out.println("Shortest distances:");
        for (int i = 0; i < n; i++)
            System.out.println(src + " -> " + i + " = " + dist[i]);
    }
}
```

### OUTPUT

```text
Enter number of nodes: 4
Enter cost matrix:
0 2 5 0
2 0 1 4
5 1 0 2
0 4 2 0
Enter source node: 0

Shortest distances:
0 -> 0 = 0
0 -> 1 = 2
0 -> 2 = 3
0 -> 3 = 5
```

### RESULT

Thus, Link State Routing was successfully simulated using Dijkstra's algorithm.

### VIVA

**Link State Routing:** Each router maintains information about the complete network topology.

**Algorithm used:** Dijkstra's shortest path algorithm.

**Remember:** Link State → **Dijkstra → Smallest unvisited node**
---
## 10) Simulation of Error Detection Using CRC

### AIM

To simulate error detection using Cyclic Redundancy Check (CRC) at the sender and receiver sides.

### ALGORITHM

1. Enter the data and generator in binary.
2. Append zeros to the data.
3. Perform binary XOR division using the generator.
4. Obtain the CRC remainder.
5. Append the remainder to the data to form the transmitted code.
6. At the receiver, divide the received code using the same generator.
7. If the remainder is all zeros, display "No Error".
8. Otherwise, display "Error Detected".

### JAVA PROGRAM

```java id="crcmain"
import java.util.*;

class CRC {
    static String divide(String data, String gen) {
        char[] a = data.toCharArray();

        for (int i = 0; i <= a.length - gen.length(); i++) {
            if (a[i] == '1') {
                for (int j = 0; j < gen.length(); j++)
                    a[i + j] = (a[i + j] == gen.charAt(j)) ? '0' : '1';
            }
        }

        return new String(a).substring(a.length - gen.length() + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter data: ");
        String data = sc.next();

        System.out.print("Enter generator: ");
        String gen = sc.next();

        String zeros = "0".repeat(gen.length() - 1);
        String crc = divide(data + zeros, gen);
        String code = data + crc;

        System.out.println("CRC Remainder: " + crc);
        System.out.println("Transmitted Code: " + code);

        System.out.print("Enter received code: ");
        String received = sc.next();

        String rem = divide(received, gen);

        if (rem.contains("1"))
            System.out.println("Error Detected");
        else
            System.out.println("No Error");
    }
}
```

### OUTPUT — NO ERROR

```text
Enter data: 110101
Enter generator: 1011
CRC Remainder: 111
Transmitted Code: 110101111
Enter received code: 110101111
No Error
```

### OUTPUT — ERROR

```text
Enter data: 110101
Enter generator: 1011
CRC Remainder: 111
Transmitted Code: 110101111
Enter received code: 110100111
Error Detected
```

### RESULT

Thus, error detection was successfully simulated using the Cyclic Redundancy Check (CRC) technique.

### VIVA

**CRC:** Cyclic Redundancy Check is an error-detection technique used to detect errors in transmitted data.

**Sender:** Calculates the CRC remainder and appends it to the data.

**Receiver:** Divides the received code using the same generator.

**Remainder = 0:** No error.

**Remainder ≠ 0:** Error detected.

**Remember:** Data + Zeros → XOR Division → CRC → Transmit → Divide Again
---
