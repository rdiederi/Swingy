package controller;

import model.DeathNight;
import model.Warrior;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        StorageController sC = new StorageController();
        Warrior warrior = new DeathNight();
        sC.writeToFile(warrior);
        sC.readFile();
        ConsoleController.gameLoop();
    }
}
