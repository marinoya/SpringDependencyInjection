package edu.progress.springdependencyinjection;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientThread extends Thread {

    public Socket socket;

    private DBQueryMaker queryMaker;



    public ClientThread(Socket client) {
        this.socket = client;
    }

    public void setQueryMaker(DBQueryMaker queryMaker){
        this.queryMaker =queryMaker;
    }

    public DBQueryMaker getQueryMaker() {
        return queryMaker;
    }

    @Override
    public void run(){
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            while(true){
                String clientMessage = reader.readLine();
                if (clientMessage.equals("exit"))
                    break;
                System.out.println(clientMessage);

                if (clientMessage.trim().toLowerCase().equals("show cities")){
                    if(queryMaker != null){
                        List<Object[]> result = queryMaker.select("SELECT Name FROM city");
                        result.stream().map(o->o[0]).forEach(writer::println);
                        writer.flush();
                    }
                }

            }







        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (writer!=null)
                    writer.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
