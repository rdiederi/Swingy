package controller;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JASONArray;

import model.Warrior;

import java.io.*;
import java.io.FileReader;

public class StorageController {
    public static void readFile(){
        Object obj = new JSONParser().parse(new FileReader("src/main/database/save.txt"));
    }

    public static void writeToFile(Warrior hero) throws IOException {
        try {
            JSONObject message = new JSONObject();

            message.put("attack", "10");
            message.put("defence", "15");
            message.put("lvl", "1");
            message.put("hp", "100");

            File file = new File("src/main/database/save.txt");
            String str = "Hello1!";
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(message.toString());

            writer.close();
            System.out.println("Message Saved");
        }
        catch (IOException e) {
            System.out.println("Message Not Saved:" + e);
        }
    }
////STEP 1. Import required packages
//
//    // JDBC driver name and database URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost/swing";
//
//    //  Database credentials
//    static final String USER = "username";
//    static final String PASS = "password";
//
//    public static void main(String[] args) {
//        Connection conn = null;
//        Statement stmt = null;
//        try{
//            //STEP 2: Register JDBC driver
//            Class.forName("com.mysql.jdbc.Driver");
//
//            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT id, first, last, age FROM Employees";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            //STEP 5: Extract data from result set
//            while(rs.next()){
//                //Retrieve by column name
//                int id  = rs.getInt("id");
//                int age = rs.getInt("age");
//                String first = rs.getString("first");
//                String last = rs.getString("last");
//
//                //Display values
//                System.out.print("ID: " + id);
//                System.out.print(", Age: " + age);
//                System.out.print(", First: " + first);
//                System.out.println(", Last: " + last);
//            }
//            //STEP 6: Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
//        }catch(SQLException se){
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }catch(Exception e){
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        }finally{
//            //finally block used to close resources
//            try{
//                if(stmt!=null)
//                    stmt.close();
//            }catch(SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
//        }//end try
//        System.out.println("Goodbye!");
//    }//end main
}//end FirstExample
