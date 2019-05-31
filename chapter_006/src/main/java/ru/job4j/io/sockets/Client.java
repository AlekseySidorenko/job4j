package ru.job4j.io.sockets;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class Client | Task Solution: Bot [#7921]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 28.05.2019
 */
public class Client {

    private final Socket socket;

    Client(Socket socket) {
        this.socket = socket;
    }

    /**
     * Start client.
     */
    public void start() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner console = new Scanner(System.in);
            String request;
            String response;
            do {
                System.out.println("Enter your message:");
                request = console.nextLine();
                out.println(request);
                response = in.readLine();
                while (!response.isEmpty()) {
                    response = in.readLine();
                    System.out.println(response);
                }
            } while (!"exit".equalsIgnoreCase(request));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main.
     * @throws IOException IOException.
     */
    public static void main(String[] args) throws IOException {
        try (final Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5000)) {
            new Client(socket);
        }
    }
}