import java.io.*;
import java.net.*;

public class vpn {
    public static void main(String[] args) {
        try {
            // Create a server socket that listens on a specific port
            ServerSocket serverSocket = new ServerSocket(12345);

            while (true) {
                // Wait for a client to connect
                System.out.println("Waiting for client to connect...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected!");

                // Create a new thread to handle the client connection
                Thread clientThread = new Thread(new VPNClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class VPNClientHandler implements Runnable {
    private Socket clientSocket;

    public VPNClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            // Read data from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String clientData = in.readLine();
            System.out.println("Received data from client: " + clientData);

            // Send data back to the client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Hello, client!");

            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
