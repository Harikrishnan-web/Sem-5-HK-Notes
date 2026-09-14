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
