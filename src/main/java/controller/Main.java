package controller;

import model.DeathNight;
import model.Warrior;

import java.io.IOException;
import java.sql.SQLException;

public class Main{
    public static void main(String[] args) throws IOException, SQLException {
        StorageController sc = new StorageController();
        ConsoleController.gameLoop();
    }
}
