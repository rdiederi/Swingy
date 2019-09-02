package za.co.swingy.controller;

import za.co.swingy.model.Hero;

import java.sql.*;

public class StorageController {
    private static Connection cnx;
    private Statement stmt;
    private final static String db = "swingy";

    public StorageController() {
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1:", "user", "password");
            stmt = cnx.createStatement();
        } catch (Exception e){
            System.out.println("ERROR: " + "[" + new Exception().getStackTrace()[0].getMethodName() + "]" + " -> " + e.getMessage());
        }
    }

    void createDB()
    {
        String query =  "CREATE DATABASE IF NOT EXISTS " + db;
        try (Statement stm = cnx.createStatement()){
            stm.executeUpdate(query);
        } catch (SQLException e)
        {
            System.out.println("ERROR: " + "[" + new Exception().getStackTrace()[0].getMethodName() + "]" + " -> " + e.getMessage());
        }
    }

    void createTB(){

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
            System.out.println("ERROR: " + "[" + new Exception().getStackTrace()[0].getMethodName() + "]" + " -> " + e.getMessage());
        }
    }

    private void setHeroId(Hero hero) {
        int id = 0;
        try {
            String query = "SELECT id FROM " + db + ".hero " +
                    "WHERE name = '"+hero.getName()+"';";
            ResultSet resultSet;

            resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            hero.setId(id);
        } catch (SQLException e) {
            System.out.println("ERROR: " + "[" + new Exception().getStackTrace()[0].getMethodName() + "]" + " -> " + e.getMessage());
        }
    }

    void saveHero(Hero hero)
    {
        String query = "INSERT INTO "+ db + ".hero (attack, defense, hp, lvl, xp, name, type) VALUES (?,?,?,?,?,?,?);";
        try
        {
            PreparedStatement statement;
            statement = cnx.prepareStatement(query);
            statement.setInt(1, hero.getAttack());
            statement.setInt(2, hero.getDefense());
            statement.setInt(3, hero.getHp());
            statement.setInt(4, hero.getLvl());
            statement.setInt(5, hero.getXp());
            statement.setString(6, hero.getName());
            statement.setString(7, hero.getType());

            statement.executeUpdate();
            setHeroId(hero);
        }
        catch (SQLException e)
        {
            System.out.println("ERROR: " + "[" + new Exception().getStackTrace()[0].getMethodName() + "]" + " -> " + e.getMessage());
        }
    }

    ResultSet loadGameData() {
        ResultSet resultSet;
        try {
            String query = "SELECT * FROM " + db + ".hero;";
            resultSet = stmt.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            System.out.println("ERROR: " + "[" + new Exception().getStackTrace()[0].getMethodName() + "]" + " -> " + e.getMessage());
        }
        return null;
    }

    static Hero loadHero(ResultSet resultSet, int rowCount) {
        try {
            Factory factory = new Factory();
            while (resultSet.next() && rowCount > 1)
                --rowCount;
            Hero hero = factory.newHero(resultSet.getString("type"), resultSet.getString("name"));
            hero.setId(resultSet.getInt("id"));
            hero.setXp(resultSet.getInt("xp"));
            hero.setLvl(resultSet.getInt("lvl"));
            hero.setAttack(resultSet.getInt("attack"));
            hero.setDefense(resultSet.getInt("defense"));
            hero.setHp(resultSet.getInt("hp"));

            return hero;
        } catch (SQLException e){
            System.out.println("ERROR: " + "[" + new Exception().getStackTrace()[0].getMethodName() + "]" + " -> " + e.getMessage());
        }
        return null;
    }

    public void updateStats(Hero hero) {
        try {
            String query = "UPDATE " + db + ".hero " +
                    "SET attack = ?, defense = ?, hp = ?, lvl = ?, xp = ? " +
                    "WHERE id = " + hero.getId() + ";";

            PreparedStatement statement = cnx.prepareStatement(query);

            statement.setInt(1, hero.getAttack());
            statement.setInt(2, hero.getDefense());
            statement.setInt(3, hero.getHp());
            statement.setInt(4, hero.getLvl());
            statement.setInt(5, hero.getXp());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: " + "[" + new Exception().getStackTrace()[0].getMethodName() + "]" + " -> " + e.getMessage());
        }
    }
}
