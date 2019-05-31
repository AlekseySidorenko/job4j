package ru.job4j.io.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class Server | Task Solution: Bot [#7921]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 28.05.2019
 */
public class Server {
    private final Socket socket;
    private final String[] answers = {"Do your best", "Kill them all", "F*ck it"};

    Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Get random answer for client.
     * @return Answer.
     */
    private String getRandomAnswer() {
        int randomIndex = (int) (Math.random() * answers.length);
        return answers[randomIndex];
    }

    /**
     * Start server.
     */
    public void start() {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String ask;
            do {
                System.out.println("Waiting for command...");
                ask = in.readLine();
                System.out.println(ask);
                if ("hello".equalsIgnoreCase(ask)) {
                    out.println("Hello, my friend");
                } else if ("exit".equalsIgnoreCase(ask)) {
                    out.println("See u next time");
                } else {
                    out.println(getRandomAnswer());
                }
            } while (!"exit".equalsIgnoreCase(ask));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main.
     */
    public static void main(String[] args) throws IOException {
        try (final Socket socket = new ServerSocket(5000).accept()) {
            new Server(socket);
        }
    }
}