package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

// Class representing a simple server implementation
public class MyServer {
    int port; // Port number on which the server listens
    boolean stop; // Flag to indicate whether the server should stop
    ClientHandler ch; // Client handler for processing client requests

    // Constructor initializes the server with a port number and client handler
    public MyServer(int port, ClientHandler ch) {
        this.port = port; // Set the port number
        this.ch = ch; // Set the client handler
    }

    // Method to start the server
    public void start() {
        stop = false; // Set the stop flag to false
        // Start a new thread to handle server operations
        new Thread(() -> startServer()).start();
    }

    // Method to start the server operations
    private void startServer() {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(port); // Create a server socket with the specified port
            server.setSoTimeout(1000); // Set a timeout for accepting client connections
            while (!stop) { // Continue running the server until stop flag is set
                try {
                    client = server.accept(); // Accept incoming client connection
                    // Handle the client request using the client handler
                    ch.handleClient(client.getInputStream(), client.getOutputStream());
                    ch.close(); // Close the client handler after handling the request
                    client.close(); // Close the client socket
                } catch (SocketTimeoutException e) {
                    // Continue to next iteration if no client connection is accepted within timeout
                }
            }
        } catch (IOException e) {
            // Handle IO exception (e.g., socket creation error)
            e.printStackTrace(); // Print the stack trace for debugging
        } finally {
            ch.close(); // Close the client handler
            try {
                server.close(); // Close the server socket
            } catch (IOException e) {
                // Handle IO exception while closing the server socket
                throw new RuntimeException(e);
            }
            try {
                client.close(); // Close the client socket
            } catch (IOException e) {
                // Handle IO exception while closing the client socket
                throw new RuntimeException(e);
            }
        }
    }

    // Method to gracefully stop the server
    public void close() {
        stop(); // Stop the server
    }

    // Method to stop the server
    public void stop() {
        stop = true; // Set the stop flag to true
    }
}
