package controller;

import org.json.JSONObject;
import java.sql.*;
import model.Warrior;
import java.io.*;
import java.io.FileReader;

public class StorageController {
    private Connection cnx;
    private Statement stmt;

    public StorageController() {
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing", "user", "password");
            stmt = cnx.createStatement();
        } catch (Exception e){
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public StorageController createDB()
    {
        String query =  "CREATE DATABASE IF NOT EXISTS swing";
        try (Statement stm = cnx.createStatement()){
            stm.executeUpdate(query);
            System.out.println("DATABASE CREATED!");
        } catch (SQLException e)
        {
            System.out.println("[ERROR] " + e.getMessage());
        }
        return (this);
    }

    public StorageController createTB(){

        String createTable = "CREATE TABLE IF NOT EXISTS swing.hero("  +
                "id INTEGER(11) NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
                "attack INTEGER(11) NOT NULL , " +
                "defense INTEGER(11) NOT NULL , " +
                "hp INTEGER(11) NOT NULL , " +
                "lvl INTEGER(11) NOT NULL , " +
                "xp INTEGER(11) NOT NULL , " +
                "name VARCHAR(50) NOT NULL , " +
                "weapon INTEGER(1) NOT NULL ," +
                "armor INTEGER(1) NOT NULL ," +
                "helm INTEGER(1) NOT NULL);";

        try{
            if(stmt.executeUpdate(createTable) >= 0) {
                System.out.println("TABLE CREATED!!!");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return (this);
    }

    public void createHero(int attack, int defense, int hp, int lvl, int xp, String name, String weapon, String armor, String helm)
    {
        int type;

        String query = "INSERT INTO hero (attack, defense, hp, lvl, xp, name, weapon, armor, helm) VALUES (?,?,?,?,?,?,?,?,?);";
        try
        {
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, attack);
            statement.setInt(2, defense);
            statement.setInt(3, hp);
            statement.setInt(4, lvl);
            statement.setInt(5, xp);
            statement.setString(6, name);
            statement.setString(7, weapon);
            statement.setString(8, armor);
            statement.setString(9, helm);

            int count = statement.executeUpdate();
            if (count > 0)
                System.out.println("Record created");
        }
        catch (SQLException e)
        {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
}
