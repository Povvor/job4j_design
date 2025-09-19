package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class EchoServer {

    public static Map<String, String> parseArgs(String string) {
        Map<String, String> values = new HashMap<>();
        string = string.substring(string.indexOf("?") + 1);
        string = string.substring(0, string.indexOf(" "));
        String[] split = string.split("&");
        for (String string2 : split) {
            String[] split2 = string2.split("=");
            values.put(split2[0], split2[1]);
        }
        return values;
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    Map<String, String> values = parseArgs(string);
                    if (values.containsKey("msg")) {
                        switch (values.get("msg")) {
                            case "Hello":
                                output.write("Hello".getBytes());
                                break;
                            case "Bye":
                                output.write("Bye".getBytes());
                                server.close();
                                break;
                            default:
                                output.write("Wrong message".getBytes());
                        }
                        output.flush();

                    }
                }
            }
        }
    }
}