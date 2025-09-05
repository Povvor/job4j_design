package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final List<String> log = new ArrayList<>();
    private boolean isChatActive = true;
    private List<String> answers = new ArrayList<>();
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        readPhrases();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String lastMessage = "";

        while (!lastMessage.equals(OUT)) {
            String message = scanner.nextLine();
            log.add(message);
            switch (message) {
                case STOP:
                    isChatActive = false;
                    break;
                case CONTINUE:
                    isChatActive = true;
                    break;
                case OUT:
                    isChatActive = false;
                    break;
                default:
            }
            lastMessage = message;

            if (isChatActive) {
                String answer = answers.get(ThreadLocalRandom.current().nextInt(answers.size()));
                System.out.println(answer);
                log.add(answer);
            }
        }
    }

    private void readPhrases() {
        try (BufferedReader input = new BufferedReader(new FileReader(botAnswers))) {
            answers = input.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chatLog.txt", "data/answers.txt");
        consoleChat.run();
        consoleChat.saveLog(consoleChat.log);
    }
}