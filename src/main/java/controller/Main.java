package controller;

import model.DeathNight;
import model.Warrior;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        StorageController sc = new StorageController();
        sc.createDB();
        sc.createTB();
        ConsoleController.gameLoop();
    }
}
