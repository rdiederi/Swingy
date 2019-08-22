package controller;


import model.DeathNight;
import model.Druid;
import model.Hunter;
import model.Warrior;
import view.ConsoleView;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleController {

    public static Warrior nameHero(int type, Scanner input) {
        Warrior hero = null;

        String types[] = {"Druid", "DeathNight", "Hunter"};

        System.out.println("Give your " + types[type - 1] + " a name:");
        System.out.print(">> ");
        String name = input.nextLine();
        switch (type) {
            case 1:
                hero = new Druid(name);
                break;
            case 2:
                hero = new DeathNight(name);
                break;
            case 3:
                hero = new Hunter(name);
                break;

        }
        return hero;
    }

    public static Warrior createHero() {
        String name;
        Warrior hero = null;

        ConsoleView.printHeroClasses();
        System.out.print(">> ");

        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();

        //Save Selection
        if (Integer.parseInt(cmd) > 0 && Integer.parseInt(cmd) < 4) {
            hero = nameHero(Integer.parseInt(cmd), input);
        } else {
            System.out.println("Wrong! Try Again!");
            createHero();
        }
        return hero;
    }

    public static void gameLoop() throws IOException {
        Warrior hero = null;

        while (true){
            ConsoleView.printWelcome();
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            int cmd = input.nextInt();
            switch (cmd){
                case 1:
                    hero = createHero();
//                    storageController.writeToFile(hero);
                    //gameStart(hero);
                    break;
                case 2:
                    //Load Game
                    System.out.println("Load your game!");
                    break;
                case 3:
                    //Exit
                    System.out.println("Good Bye!!\nCome again");
                    System.exit(0);
                    break;
                default:
                    System.out.println("You Fucked Up!\nTry Again!\n\n");
                    ConsoleView.printWelcome();
            }
        }
    }
}
