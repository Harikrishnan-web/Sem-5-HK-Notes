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
