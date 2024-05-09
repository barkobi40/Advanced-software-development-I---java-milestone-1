package test;

import java.io.*;
import java.util.Scanner;

// Class responsible for handling client requests in a book scrabble game
public class BookScrabbleHandler implements ClientHandler {
    PrintWriter out; // PrintWriter for sending responses to the client
    Scanner in; // Scanner for reading input from the client

    // Method to handle client requests
    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        Boolean answer = null; // Variable to store the response to the client's request
        try {
            // Initialize the input and output streams
            in = new Scanner(new BufferedReader(new InputStreamReader(inFromClient)));
            out = new PrintWriter(outToClient, true);

            // Read the client's input
            String line = in.next();
            String[] books = line.substring(2).split(","); // Extract book names from the input

            // Process the client's request based on the input command
            if (line.startsWith("Q")) {
                // If the command is "Q", query the dictionaries for the existence of the word
                answer = DictionaryManager.get().query(books);
            } else if (line.startsWith("C")) {
                // If the command is "C", challenge the word by searching in the dictionaries
                answer = DictionaryManager.get().query(books);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace in case of an exception
        } finally {
            // Send the response to the client
            out.println(answer.toString() + "\n");
        }
    }

    // Method to close resources after handling client requests
    public void close() {
        try {
            in.close(); // Close the input stream
            out.close(); // Close the output stream
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace in case of an exception
        }
    }
}
