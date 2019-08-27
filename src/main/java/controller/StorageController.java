package controller;

import model.Hero;

import java.sql.*;

public class StorageController {
    private Connection cnx;
    private Statement stmt;
    private final String db = "swingy";

    public StorageController() {
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/", "root", "password");
            stmt = cnx.createStatement();
        } catch (Exception e){
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    public StorageController createDB()
    {
        String query =  "CREATE DATABASE IF NOT EXISTS " + db;
        try (Statement stm = cnx.createStatement()){
            stm.executeUpdate(query);
        } catch (SQLException e)
        {
            System.out.println("[ERROR] " + e.getMessage());
        }
        return (this);
    }

    public StorageController createTB(){

        String createTable = "CREATE TABLE IF NOT EXISTS " + db + ".hero" +
                "(id INTEGER(11) NOT NULL AUTO_INCREMENT PRIMARY KEY , " +
                "attack INTEGER(11) NOT NULL , " +
                "defense INTEGER(11) NOT NULL , " +
                "hp INTEGER(11) NOT NULL , " +
                "lvl INTEGER(11) NOT NULL , " +
                "xp INTEGER(11) NOT NULL , " +
                "name VARCHAR(50) NOT NULL , " +
                "type VARCHAR(50) NOT NULL);";

        try{
            stmt.executeUpdate(createTable);
        }catch(SQLException e){
            System.out.println(e);
        }
        return (this);
    }

    public void saveHero(Hero hero)
    {
        int type;

        String query = "INSERT INTO "+ db + ".hero (attack, defense, hp, lvl, xp, name, type) VALUES (?,?,?,?,?,?, ?);";
        try
        {
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, hero.getAttack());
            statement.setInt(2, hero.getDefense());
            statement.setInt(3, hero.getHp());
            statement.setInt(4, hero.getLvl());
            statement.setInt(5, hero.getXp());
            statement.setString(6, hero.getName());
            statement.setString(7, hero.getType());

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

//    public void updateHero(Hero hero) {
//
//    }
}
