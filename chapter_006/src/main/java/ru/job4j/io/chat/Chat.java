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
        if (validateArgs(args)) {
            answers = this.getAnswers(this.parseArgs(args));
            startDialogue();
        }
    }

    /**
     * Validate arguments from command line.
     * @param args Arguments.
     * @return Validate result.
     */
    boolean validateArgs(String[] args) {
        boolean result = false;
        if (args.length == 2 && args[0].equalsIgnoreCase("-file") && !args[1].isEmpty()) {
            result = true;
        } else {
            System.out.println("Аргументы программы должны быть в следующем виде:");
            System.out.println("-file path_filename.txt");
        }
        return result;
    }

    /**
     * Parse arguments from command line.
     * @param args Arguments.
     */
    public String parseArgs(String[] args) {
        String path = args[1];
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
            while (!FINISH.equalsIgnoreCase(userInput)) {
                userInput = scanner.nextLine();
                if (STOP.equalsIgnoreCase(userInput) || FINISH.equalsIgnoreCase(userInput)) {
                    needAnswer = false;
                } else if (CONTINUE.equalsIgnoreCase(userInput)) {
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
        new Chat(args);
    }
}