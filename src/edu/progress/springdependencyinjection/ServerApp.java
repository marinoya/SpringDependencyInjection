package edu.progress.springdependencyinjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.Beans;
import java.io.IOException;

public class ServerApp {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Server server = (Server)context.getBean("server");
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
