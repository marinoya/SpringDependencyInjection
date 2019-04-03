package edu.progress.springdependencyinjection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBQueryMaker {


        private Connection connection;
        private String url;
        private String username;
        private String password;




        public DBQueryMaker(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        public List<Object[]> select(String query, Object...parameters){

            List<Object[]> result = new ArrayList<>();
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                for(int i = 0; i < parameters.length; i++) {
                    statement.setObject(i+1, parameters[i]);
                }
                ResultSet rs = statement.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while(rs.next()) {
                    Object[] row = new Object[columnsNumber];
                    for(int i = 0; i < columnsNumber; i++) {
                        row[i] = rs.getObject(i+1);
                    }
                    result.add(row);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return result;
        }


        public void connect () throws SQLException, ClassNotFoundException{

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(
                        url,
                        username,
                        password);
            }

        public  void disconnect() throws SQLException{
            if (connection != null) {

                    connection.close();



            }
        }





}
