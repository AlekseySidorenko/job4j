package ru.job4j.io.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class AbuseWordsRemover | Task Solution: Console chat implementation [#862]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 21.05.2019
 */
public class Chat {

    private static final String FINISH = "exit";
    private static final String STOP = "stop";
    private static final String CONTINUE = "go";
    private final String separator = System.lineSeparator();
    private File log = new File("chatlog.txt");
    private List<String> answers;

    public Chat() {
    }

    public Chat(String[] args) {
        answers = this.getAnswers(this.parseArgs(args));
    }

    /**
     * Parse arguments from command line.
     * @param args Arguments.
     */
    public String parseArgs(String[] args) {
        String path = null;
        if (args.length == 2 && args[0].equalsIgnoreCase("-file") && !args[1].isEmpty()) {
            path = args[1];
        } else {
            System.out.println("Аргументы программы должны быть в следующем виде:");
            System.out.println("-file path_filename.txt");
            System.exit(0);
        }
        return path;
    }

    /**
     * Get answers from text file.
     * @param path Path to file.
     * @return List of answers.
     */
    public List<String> getAnswers(String path) {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String answer;
            while ((answer = in.readLine()) != null) {
                answers.add(answer);
            }
        } catch (IOException e) {
            System.out.println("Invalid filepath");
        }
        return answers;
    }

    /**
     * Get random answer.
     * @return Random answer.
     */
    public String getRandomAnswer() {
        int random = (int) (Math.random() * answers.size());
        return answers.get(random);
    }

    /**
     * Dialogue with the user.
     */
    public void startDialogue() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String answer;
        boolean needAnswer = true;
        try (PrintWriter resultLog = new PrintWriter(new FileOutputStream(log))) {
            while (!userInput.equalsIgnoreCase(FINISH)) {
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase(STOP) || userInput.equalsIgnoreCase(FINISH)) {
                    needAnswer = false;
                } else if (userInput.equalsIgnoreCase(CONTINUE)) {
                    needAnswer = true;
                }
                resultLog.write(userInput + separator);
                answer = getRandomAnswer();
                if (needAnswer) {
                    System.out.println(answer);
                    resultLog.write(answer + separator);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main.
     */
    public static void main(String[] args) {
        Chat chat = new Chat(args);
        chat.startDialogue();
    }
}