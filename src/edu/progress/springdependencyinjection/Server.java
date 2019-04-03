package edu.progress.springdependencyinjection;

import java.io.IOException;

        import edu.progress.springdependencyinjection.DBQueryMaker;

        import java.io.*;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.net.Socket;

public class Server {

    private int port;
    private DBQueryMaker queryMaker;

    public Server (int port){
        this.port = port;
    }

    public void setQueryMaker(DBQueryMaker queryMaker){
        this.queryMaker=queryMaker;
    }

    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {

            Socket client = serverSocket.accept();
            ClientThread ct = new ClientThread(client);
            ct.setQueryMaker(queryMaker);
            ct.start();

        }
    }

}
