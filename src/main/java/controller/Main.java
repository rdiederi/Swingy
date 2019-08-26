package controller;

import model.DeathNight;
import model.Warrior;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        StorageController sC = new StorageController();
        sC.createTB();
        sC.createHero(20, 20, 20,20,100,"Larry", "Sword", "Metal", "Metal");
        ConsoleController.gameLoop();
    }
}
