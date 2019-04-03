package edu.progress.springdependencyinjection;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // write your code here
        try {
            Socket socket = new Socket("127.0.0.1", 8000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            Scanner console = new Scanner(System.in);
            while (true){
                String message = console.nextLine();
                writer.println(message);
                writer.flush();
                reader.lines().forEach(System.out::println);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } {
        }

    }
}
